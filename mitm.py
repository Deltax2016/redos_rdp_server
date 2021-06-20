import socket
import os
from threading import Thread
import threading

clients = {} # The clients we have connected to
clients_lock = threading.Lock()
adr = []

def listener(client, address):
    print("Accepted connection from: ", address)
    with clients_lock:
        clients[str(address)] = client # Add a client to our list
        adr.append(str(address))
        print(clients)
    try:    
        while True:
            data = client.recv(4096)
            if not data:
                break
            else:
                #print(repr(data))
                # Here you need to read your data
                # and figure out who you want to send it to
                if str(address) == adr[0] and len(adr)>1:
                    client_to_send_to = adr[1] # Send this data to client 1
                else:
                    client_to_send_to = adr[0]
                print(len(adr))
                clients[client_to_send_to].sendall(data)
    except KeyboardInterrupt:
        pass
    finally:
        with clients_lock:
            del clients[str(address)]
            client.close()
    

host = '0.0.0.0'
port = 3000

s = socket.socket()
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
s.bind((host,port))
s.listen(3)
th = []

while True:
    print("Server is listening for connections...")
    client, address = s.accept()
    th.append(Thread(target=listener, args = (client,address)).start())

s.close()
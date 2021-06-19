import sys
import socket
print('ok')
HOST = "0.0.0.0"
PORT = 3000

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print("Socket created")

try:
    s.bind((HOST, PORT))
except Socket.error as msg:
    print("Bind failed.")
    print("Error code: "+str(msg[0]))
    print("Message: "+str(msg[1]))
    sys.exit()
print("Socket bind complete")

s.listen(10)
print("Socket now listening")

while(1):
    conn, addr = s.accept()
    print("Connected with: "+addr[0])
    #Waits for any incoming data and echoes it back
    while(1):
        data = conn.recv(1024)
        #break if not found to get to close statement which was never reached in old code
        if not data: break 
        print("Received data:", data.decode())
        conn.sendall(data)
    conn.close() 
import pickle
import socket
import struct

import cv2

HOST = '0.0.0.0'
PORT = 3000

conn=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
conn.connect(('localhost',3000))


data = b'' ### CHANGED
payload_size = struct.calcsize("L") ### CHANGED

while True:
    a = conn.recv(4096)
    

    # Retrieve message size
    while len(data) < payload_size:
        data += a

    packed_msg_size = data[:payload_size]
    data = data[payload_size:]
    msg_size = struct.unpack("L", packed_msg_size)[0] ### CHANGED

    # Retrieve all data based on message size
    while len(data) < msg_size:
        data += a


    frame_data = data[:msg_size]
    data = data[msg_size:]

    # Extract frame
    frame = pickle.loads(frame_data)
    print(frame_data)
    # Display
    cv2.imshow('frame', frame)
    cv2.waitKey(1)
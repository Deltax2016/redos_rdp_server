import cv2
import numpy as np
import socket
import sys
import pickle
import struct
clientsocket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
clientsocket.connect(('localhost',3000))

from mss import mss
from PIL import Image

mon = {'top': 0, 'left': 0, 'width': 800, 'height': 800}

sct = mss()

while 1:
    with mss() as sct :
        img = np.array(sct.grab(mon))
        #cv2.imshow('test', np.array(img))
        data = pickle.dumps(img)
        message_size = struct.pack("L", len(data))
        clientsocket.sendall(message_size + data)
        if cv2.waitKey(25) & 0xFF == ord('q'):
            cv2.destroyAllWindows()
            break

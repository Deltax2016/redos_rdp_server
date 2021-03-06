from flask import Flask, render_template
from flask_socketio import SocketIO

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret!'
socketio = SocketIO(app,logger=True, engineio_logger=True)

@socketio.on('message')
def handle_message(data):
    print('received message: ' + data)

if __name__ == '__main__':
    socketio.run(app,host='0.0.0.0',port=3000)
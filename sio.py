from flask import Flask, render_template
from flask_socketio import SocketIO, emit, join_room, leave_room, close_room, rooms, disconnect

session = {}

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret!'
socketio = SocketIO(app,logger=True, engineio_logger=True)

@socketio.on('message')
def handle_message(data):
    print('received message: ' + data)

@socketio.on('join', namespace='/test')
def join(message):
    join_room(message['room'])
    session['receive_count'] = session.get('receive_count', 0) + 1
    print(message)
    emit('my_response',
         {'data': 'In rooms: ' + ', '.join(rooms()),
'count': session['receive_count']})

if __name__ == '__main__':
    socketio.run(app,host='0.0.0.0',port=3000)
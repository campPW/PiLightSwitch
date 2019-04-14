import socket
from Switch import Switch

SERVER_IP = '10.0.0.50'
TCP_PORT = 5091
SUCCESS_MSG = 'Data transfer succesful'

def createSocket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((SERVER_IP, TCP_PORT))
    s.listen(1)
    print('Listening...')
    return s

def createConnection(socket):
    connection, address = socket.accept()
    print 'The server has been accessed by:', address
    return connection

socket = createSocket()
switch = Switch(17)

while True:
    connection = createConnection(socket)
    data = connection.recv(1024)
    print "Receieved Data: "
    print(data)
    connection.send(SUCCESS_MSG)

    if data == "ON":
        switch.flipSwitchOn()
        switch.getState()
    elif data == "OFF":
        switch.flipSwitchOff()
        switch.getState()
    else:
        print("Invalid Input")
    print("Listening...")



import socket
from Switch import Switch

SERVER_IP = '10.0.0.50'
TCP_PORT = 5091
SUCCESS_MSG = 'Data transfer succesful'

def createSocket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((SERVER_IP, TCP_PORT))
    s.listen(1)
    return s

def acceptConnection(socket):
    connection, address = socket.accept()
    print 'Server accessed by:', address[0] + " on port " + str(address[1])
    return connection

socket = createSocket()
switch = Switch(17)

while True:
    print('Waiting for connection...')
    connection = acceptConnection(socket)

    data = connection.recv(1024)
    print "Receieved Data:", data

    if data == "ON":
        switch.flipSwitchOn()
        switch.getState()
    elif data == "OFF":
        switch.flipSwitchOff()
        switch.getState()
    else:
        print("Invalid Input")

    connection.send(SUCCESS_MSG)
    connection.close()
    


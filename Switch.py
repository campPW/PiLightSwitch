import RPi.GPIO as GPIO
from time import sleep

class Switch:
    
    def __init__(self, channel):
        self.channel = channel
        self.setupGPIO()

    def setupGPIO(self):
        GPIO.setmode(GPIO.BCM)
        GPIO.setwarnings(False)
        GPIO.setup(self.channel, GPIO.OUT)
    
    def flipSwitchOn(self):
        GPIO.output(self.channel, True)
        sleep(2)

    def flipSwitchOff(self):
        GPIO.output(self.channel, False)
        sleep(2)

    def getState(self):
        state = GPIO.input(self.channel)
        if state == 1:
            print("The switch is on")
        else:
            print("The switch is off")

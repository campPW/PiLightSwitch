import RPi.GPIO as GPIO


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

    def flipSwitchOff(self):
        GPIO.output(self.channel, False)

    def getState(self):
        state = GPIO.input(self.channel)
        if state == 1:
            print("The switch is on")
        else:
            print("The Switch is off")

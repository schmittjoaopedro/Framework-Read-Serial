#include "Arduino.h"
#include "Utilities.h"
#include "Led.h"
#include "Servo.h"
#include "CommunicationServo.h"
#include "MyServo.h"

Utilities myEasy;
CommunicationServo firstFilter;
MyServo myServo1;

void setup(){
	Serial.begin(9600);
	myServo1.setup(7);
}

void loop(){
	String readData = myEasy.readString();
	int servoActive = firstFilter.readServo(readData);
	switch(servoActive){
		case 7:
			myServo1.move(readData);
		break;
	}
	delay(500);
}

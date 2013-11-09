#include "Arduino.h"
#include "Utilities.h"
#include "Led.h"
#include "Servo.h"
#include "CommunicationServo.h"
#include "MyServo.h"

Utilities myEasy;
CommunicationServo firstFilter;
MyServo myServo1;
MyServo myServo2;
MyServo myServo3;
MyServo myServo4;

void setup(){
	Serial.begin(9600);
	myServo1.setup(7);
	myServo2.setup(8);
	myServo3.setup(9);
	myServo4.setup(10);
}

void loop(){
	String readData = myEasy.readString();
	int servoActive = firstFilter.readServo(readData);
	switch(servoActive){
		case 7:
			myServo1.move(readData);
		break;
		case 8:
			myServo2.move(readData);
		break;
		case 9:
			myServo3.move(readData);
		break;
		case 10:
			myServo4.move(readData);
		break;
	}
	delay(500);
}
/*
  ADXL335
  note:vcc-->5v ,but ADXL335 Vs is 3.3V
  The circuit:
      5V: VCC
  analog 1: x-axis
  analog 2: y-axis
  analog 3: z-axis
*/

const int xpin = 0;                  // x-axis of the accelerometer
const int ypin = 1;                  // y-axis
const int zpin = 2;                  // z-axis (only on 3-axis models)
void setup()
{
  // initialize the serial communications:
  Serial.begin(9600);
}
void loop()
{
  int x = analogRead(xpin);  //read from xpin

  int y = analogRead(ypin);  //read from ypin

  int z = analogRead(zpin);  //read from zpin

  float valor_x , valor_y , valor_z;

  float zero_G = 512.0; //ADC is 0~1023  the zero g output equal to Vs/2
  //ADXL335 power supply by Vs 3.3V
  float scale = 102.3;  //ADXL335330 Sensitivity is 330mv/g
  //330 * 1024/3.3/1000

  valor_x = ((float) x - 331.5) / 65 * 9.8;
  valor_y = ((float) y - 329.5) / 68.5 * 9.8;
  valor_z = ((float) z - 340) / 68 * 9.8;
  Serial.print("x" + String(valor_x) + "y" + String(valor_y) + "z" + String(valor_z) + "t"); //se imprimen los valores para la comunicacion con java
}

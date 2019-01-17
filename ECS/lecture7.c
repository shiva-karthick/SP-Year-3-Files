#define Count 23                 /* number of values in table */
#define DACPort *((char *) 0x133 /* DAC Port */
const unsigned char WaveTable[Count] =
    {102 102, 128, 153, 174, 190, 201, 204, 201, 190, 174, 153, 128,
     102, 76, 51, 30, 14, 0, 3, 14, 30, 51, 76};

void main(void)
{ /* main entry for program */
    unsigned char i;
    while (1)
    {
        for (i = 0; i <= Count; i++)
        {
            Sleep(1);                /*delay */
            DACPort = WaveTblPtr[i]; /* op to data port */
        }
    }
}

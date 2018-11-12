#include "heap.h"

int main(int argc, char const *argv[])
{
    foo();
    return 0;
}

int foo()
{
    char *pBuffer; //<--nothing allocated yet (excluding the pointer itself, which is allocated here on the stack).
    bool b = 1;    // Allocated on the stack.
    if (b)
    {
        //Create 500 bytes on the stack
        char buffer[500];

        //Create 500 bytes on the heap
        pBuffer = malloc(char[500]);

    } //<-- buffer is deallocated here, pBuffer is not
} //<--- oops there's a memory leak, I should have called delete[] pBuffer;
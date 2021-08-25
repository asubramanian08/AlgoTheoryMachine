package Week8_MachineII;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * I made an Arjuns Basic Computer (ABC) which is a 16-bit computer that has 16
 * different instruction, 1 register, and 4096 (2^12) words of memory. Each word
 * is a 16-bit 2's complement integer. Each instruction (a word) contains the
 * command (first 4 bits) and a memory location (last 12 bits). Set the PC,
 * data, and program code in the .abc file given as input. The first line is the
 * PC and subsequent lines are in the format "lineNum instruction comment". The
 * FFF memory location will be used as an input and output device and will
 * correspond to java's stdin and stdout.
 * 
 * <li>The various instructions (in order) are:</li>
 * <ul>
 * <li>0 - HALT: stop running</li>
 * <li>1 - ADD: R = R + M[loc]</li>
 * <li>2 - SUB: R = R - M[loc]</li>
 * <li>3 - MULT: R = R * M[loc]</li>
 * <li>4 - DIV: R = R / M[loc]</li>
 * <li>5 - AND: R = R & M[loc]</li>
 * <li>6 - OR: R = R | M[loc]</li>
 * <li>7 - XOR: R = R ^ M[loc]</li>
 * <li>8 - LSHIFT: R = R << M[loc]</li>
 * <li>9 - STORE: M[loc] = R</li>
 * <li>A - LOAD: R = M[loc]</li>
 * <li>B - ISTORE: M[loc] = M[R]</li>
 * <li>C - ILOAD: M[R] = M[loc]</li>
 * <li>D - IFHOP: if(R==M[loc]) PC++</li>
 * <li>E - JUMP: PC = loc</li>
 * <li>F - IJUMP: PC = M[loc]</li>
 * </ul>
 * 
 * The simulator java code uses shorts as a 16-bit 2's complement word in an
 * ABC, an array of 0x1000 shorts for memory, and 2 shorts for the PC and
 * register. Initially it loads all the data needed from the input .abc file
 * (give the first part of the file name as a command line argument - don't
 * include the .abc), then is starts running the code with a fetch, increment,
 * and execute cycle. The code uses a shift and mask technique to break down an
 * instruction into a command and memory location. With some exception if the
 * memory location is 0xFFF (stdin or stdout) the code with read a hex short
 * from input or write one. Throughout the code read and parsing as an int then
 * casting to a short it done because otherwise the parsing will think some
 * larger numbers are going out of range.
 * 
 * <li>Look at Add.abc for a sample abc code and run the program from an
 * output</li>
 * 
 * @author Arjun Subramanian
 */
public class ArjunsBasicComputer {
    private static final short mem_sz = 0x1000; // 4096

    public static void main(String[] args) throws FileNotFoundException, DataFormatException {
        // LOAD or set the state
        short R = 0, M[] = new short[mem_sz], PC = 0;
        if (args.length == 0)
            throw new IllegalArgumentException("Need an input file");
        Scanner stateFile = new Scanner(new File(args[0] + ".abc"));
        if (!stateFile.hasNextLine())
            throw new DataFormatException("PC has to be declared");
        PC = (short) Integer.parseInt(stateFile.nextLine(), 16);
        while (stateFile.hasNextLine()) {
            String[] line = stateFile.nextLine().split(" ");
            short loc = (short) Integer.parseInt(line[0], 16);
            short val = (short) Integer.parseInt(line[1], 16);
            M[loc] = val;
        }
        stateFile.close();
        // RUN code
        short ir, cmd, loc;
        Scanner in = new Scanner(System.in);
        do {
            // fetch + increment
            ir = M[PC++];
            cmd = (short) ((ir >> 12) & 0xF);
            loc = (short) ((ir >> 0) & 0xFFF);
            // execute
            if (loc == 0xFFF && (cmd != 9 && cmd != 11 && cmd != 0))
                M[loc] = (short) in.nextInt(16);
            switch (cmd) {
                case 0x1:
                    R = (short) (R + M[loc]);
                    break;
                case 0x2:
                    R = (short) (R - M[loc]);
                    break;
                case 0x3:
                    R = (short) (R * M[loc]);
                    break;
                case 0x4:
                    R = (short) (R / M[loc]);
                    break;
                case 0x5:
                    R = (short) (R & M[loc]);
                    break;
                case 0x6:
                    R = (short) (R | M[loc]);
                    break;
                case 0x7:
                    R = (short) (R ^ M[loc]);
                    break;
                case 0x8:
                    R = (short) (R << M[loc]);
                    break;
                case 0x9:
                    M[loc] = R;
                    break;
                case 0xA:
                    R = M[loc];
                    break;
                case 0xB:
                    M[loc] = M[R];
                    break;
                case 0xC:
                    M[R] = M[loc];
                    break;
                case 0xD:
                    if (R == M[loc])
                        PC++;
                    break;
                case 0xE:
                    PC = loc;
                    break;
                case 0xF:
                    PC = M[loc];
                    break;
                default: // 0x0
                    break;
            }
            if (loc == 0xFFF && (cmd == 9 || cmd == 11))
                System.out.printf("%04X", M[loc]);
        } while (cmd != 0);
        in.close();
    }
}
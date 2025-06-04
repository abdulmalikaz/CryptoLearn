import java.io.PrintWriter;
import java.util.Scanner;
public class DES {

    Scanner userInput = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(System.out, true);
    private String[] RoundKeyArray = new String[16];
    private String[] Keys = new String[16];
    private String permutatedKey = "";
    private String plainTextBinary = "";
    private String finalResult = "";
    private String encipher = "";
    private String textEncipher = "";
    private int leftSpace;

    private String RoundKey = "";
    private String originalKey = "";
    private String plainText = "";
    private int[] NumLeftShifts = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

    private int[] PC1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3,
            60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5,
            28, 20, 12, 4 };

    private int[] PC2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41,
            52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
    private int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37,
            29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
    private int[] FP = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10,
            50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
    private int[] E = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18,
            19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
    private int[][] S1 = { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
            { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
            { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
            { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };

    private int[][] S2 = { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
            { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
            { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
            { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };

    private int[][] S3 = { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
            { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
            { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
            { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };

    private int[][] S4 = { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
            { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
            { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
            { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };

    private int[][] S5 = { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
            { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
            { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
            { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };

    private int[][] S6 = { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
            { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
            { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
            { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };

    private int[][] S7 = { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
            { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
            { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
            { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };

    private int[][] S8 = { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
            { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
            { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
            { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };

    private int[] P = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13,
            30, 6, 22, 11, 4, 25 };

    public String Encryption(String plaintext) {
        /********************************************
         * 1.SUB KEYS ********************************************************
         * Generate subkeys and prepare for encryption
         ****************************************************************************************************************/
        String binaryKey;
        originalKey = "133457799BBCDFF1"; // Default 8-character key
        binaryKey = strTobin(originalKey).replace(" ", "");
        GenerateKeys(binaryKey);

        // Convert plainText to binary
        plainText = plaintext;
        String binaryPlaintext = strTobin(plainText).replace(" ", "");
        String BinaryText = binaryPlaintext;

        // Pad or truncate the binary text to ensure it's exactly 64 bits
        int plainTextLength = binaryPlaintext.length();
        if (plainTextLength < 64) {
            while (binaryPlaintext.length() < 64) {
                binaryPlaintext += "0";
            }
        } else if (plainTextLength > 64) {
            binaryPlaintext = binaryPlaintext.substring(0, 64);
        }

        plainTextBinary = binaryPlaintext;
        plainTextLength = binaryPlaintext.length();
        BinaryText = binaryPlaintext;

        int leftpadString = plainTextLength % 64;
        int loop = leftpadString != 0 ? plainTextLength / 64 + 1 : plainTextLength / 64;
        int start = 0;
        int end = 64;

        encipher = ""; // Reset encipher for each call
        textEncipher = ""; // Reset textEncipher for each call

        for (int id = loop, wordCount = 1; id > 0; id--, wordCount++) {
            int leftpad = 64 - leftpadString;
            leftSpace = leftpad % 64 != 0 ? leftpad / 8 : 0;
            end = plainTextLength - start < 64 ? plainTextLength : end;
            plainTextBinary = BinaryText.substring(start, end);
            while (plainTextBinary.length() != 64) {
                plainTextBinary = "0" + plainTextBinary;
            }

            // Initial Permutation
            String IPBinary = "";
            for (int i : IP) {
                IPBinary += plainTextBinary.charAt(i - 1);
            }

            functions(IPBinary, id);
            end += 64;
            start += 64;
        }

        return textEncipher; // Return the final encrypted string
    }

    /* Convert string to binary string */
    public String strTobin(String str) {

        byte[] bytes = str.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();
    }

    /* Convert integer to String */
    public String intTostr(String stream, int size) {

        String result = "";
        for (int i = 0; i < stream.length(); i += size) {
            result += (stream.substring(i, Math.min(stream.length(), i + size)) + " ");
        }
        String[] ss = result.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            sb.append((char) Integer.parseInt(ss[i], 2));
        }
        return sb.toString();
    }

    /* Left shift function */
    String CircularLeftShift(String s, int k) {

        String result = s.substring(k);
        for (int i = 0; i < k; i++) {
            result += s.charAt(i);
        }
        return result;
    }

    public String SboxPermutation(String result) {

        String RB1 = result.substring(0, 6); // Extract the 6-bit block
        // Determine the row and column for the S-box lookup
        String row1 = String.valueOf(RB1.substring(0, 1) + RB1.substring(5, 6)); // The first and last bits of RB1 are
        // used to determine the row.
        String col1 = String.valueOf(RB1.substring(1, 5));// The middle four bits (positions 1 to 4) determine the
        // column.
        int target = S1[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];// Use the row and column to look up the
        // S-box value : The method looks up the
        // value in S1 using the row and column:
        String binaryTarget = String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0'); // Convert the
        // S-box output
        // to a 4-bit
        // binary string

        String RB2 = result.substring(6, 12);
        row1 = String.valueOf(RB2.substring(0, 1) + RB2.substring(5, 6));
        col1 = String.valueOf(RB2.substring(1, 5));
        target = S2[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        String RB3 = result.substring(12, 18);
        row1 = String.valueOf(RB3.substring(0, 1) + RB3.substring(5, 6));
        col1 = String.valueOf(RB3.substring(1, 5));
        target = S3[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        String RB4 = result.substring(18, 24);
        row1 = String.valueOf(RB4.substring(0, 1) + RB4.substring(5, 6));
        col1 = String.valueOf(RB4.substring(1, 5));
        target = S4[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        String RB5 = result.substring(24, 30);
        row1 = String.valueOf(RB5.substring(0, 1) + RB5.substring(5, 6));
        col1 = String.valueOf(RB5.substring(1, 5));
        target = S5[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        String RB6 = result.substring(30, 36);
        row1 = String.valueOf(RB6.substring(0, 1) + RB6.substring(5, 6));
        col1 = String.valueOf(RB6.substring(1, 5));
        target = S6[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        String RB7 = result.substring(36, 42);
        row1 = String.valueOf(RB7.substring(0, 1) + RB7.substring(5, 6));
        col1 = String.valueOf(RB7.substring(1, 5));
        target = S7[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        String RB8 = result.substring(42, 48);
        row1 = String.valueOf(RB8.substring(0, 1) + RB8.substring(5, 6));
        col1 = String.valueOf(RB8.substring(1, 5));
        target = S8[Integer.parseInt(row1, 2)][Integer.parseInt(col1, 2)];
        binaryTarget += String.format("%4s", Integer.toBinaryString(target)).replace(' ', '0');

        // After processing all segments, binaryTarget accumulates a final result (32
        // bits).
        return binaryTarget;
    }

    public void GenerateKeys(String binaryKey) {
        // Key should be atleast 8 characters long


        /*
         * Get the 56-bit permutation from the original key using PC-1 omitting every
         * 8th bit.
         */
        for (int i : PC1) {
            permutatedKey += binaryKey.charAt(i - 1);
        }


        /*
         * Next, split this key into left and right halves, LK and RK, where each half
         * has 28 bits.
         */


        String LK = permutatedKey.substring(0, 28);
        String RK = permutatedKey.substring(28, 56);

        /* Now, perform 16 left circular shifts from the original LK and RK */

        String Lkey = LK;
        String Rkey = RK;

        int Index = 0;
        for (int a : NumLeftShifts) {
            Lkey = CircularLeftShift(Lkey, a);
            Rkey = CircularLeftShift(Rkey, a);
            Keys[Index] = Lkey + Rkey;
            Index++;
        }


        /* Then build the 16 48-bit sub keys using the PC-2 Permutation table */

        Index = 0;
        for (String key : Keys) {
            for (int j : PC2) {
                RoundKey += key.charAt(j - 1);

            }
            RoundKeyArray[Index] = RoundKey; // Each generated 48-bit subkey is stored in the RoundKeyArray, which holds
            // all 16 keys for use during the encryption rounds.

            Index++;
            RoundKey = "";
        }
    }

    public void functions(String IPBinary, int id) {

//      /* Divide the permuted block into two halves of 32 bits */

        String LeftIPBinary = IPBinary.substring(0, 32);
        String RightIPBinary = IPBinary.substring(32, 64);

        int counter = 1;

        for (String k : RoundKeyArray) { // // Iterate over each round key in the RoundKeyArray

            String LeftBlock = RightIPBinary; // Set the LeftBlock to the current RightIPBinary for processing

            String expand = "";

            //// Expand the right block using the expansion table E
            for (int i : E) {
                expand += RightIPBinary.charAt(i - 1); // // Expand 32 bits to 48 bits

                // Each block of 4 original bits has been expanded to a block of 6 output bits

            }

            // Create a StringBuilder to hold the XOR result
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k.length(); i++) {
                sb.append((k.charAt(i) ^ expand.charAt(i))); // // XOR the round key with the expanded block
            }
            String result = sb.toString(); // // Store the XOR result

            //// Pass the result through the S-box permutation
            String binaryTarget = SboxPermutation(result);

            // Lastly,to get f, permutate the output of the S-box using table P to obtain
            // the final value
            String function = "";
            for (int d : P) {
                function += binaryTarget.charAt(d - 1);
            }

            // Finally, XOR the previous left block with the function value to get the new
            // right block
            sb = new StringBuilder();
            for (int i = 0; i < LeftIPBinary.length(); i++) {
                sb.append((LeftIPBinary.charAt(i) ^ function.charAt(i))); // XOR to create the new RightIPBinary
            }
            result = sb.toString();

            RightIPBinary = result; // Store the new right block

            result = ""; // Reset result for the next iteration

            counter++; // // Increment round counter
            if (counter > 16) { // // After completing 16 rounds
                // Combine the two blocks to form a 64-bit block in reverse order
                result = RightIPBinary + LeftBlock;

                finalResult = "";
                // Final Permutation FP: The Inverse of the Initial permutation IP
                for (int x : FP) {
                    finalResult += result.charAt(x - 1); // Apply the final permutation
                }
                encipher += finalResult; // Append to the encrypted result
                // Handle additional formatting based on id and leftSpace
                // if true(This line effectively skips the first leftSpace characters of
                // convertedResult and appends the remaining characters to textEncipher.)
                // else ( accumulate the value of textEncipher by adding the contents of
                // convertedResult to it.)
                textEncipher += id == 1 && leftSpace != 0 ? intTostr(finalResult, 8).substring(leftSpace)
                        : intTostr(finalResult, 8);
            }

            LeftIPBinary = LeftBlock;
        }
    }

}

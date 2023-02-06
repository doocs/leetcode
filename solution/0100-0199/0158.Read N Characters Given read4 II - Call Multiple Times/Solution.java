/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    private char[] buf4 = new char[4];
    private int i;
    private int size;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int j = 0;
        while (j < n) {
            if (i == size) {
                size = read4(buf4);
                i = 0;
                if (size == 0) {
                    break;
                }
            }
            while (j < n && i < size) {
                buf[j++] = buf4[i++];
            }
        }
        return j;
    }
}
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or
 * vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal
 * to 1. public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */

class Solution {
    public int guessMajority(ArrayReader reader) {
        int n = reader.length();
        int x = reader.query(0, 1, 2, 3);
        int a = 1, b = 0;
        int k = 0;
        for (int i = 4; i < n; ++i) {
            if (reader.query(0, 1, 2, i) == x) {
                ++a;
            } else {
                ++b;
                k = i;
            }
        }

        int y = reader.query(0, 1, 2, 4);
        if (reader.query(1, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 0;
        }
        if (reader.query(0, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 1;
        }
        if (reader.query(0, 1, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 2;
        }
        if (a == b) {
            return -1;
        }
        return a > b ? 3 : k;
    }
}
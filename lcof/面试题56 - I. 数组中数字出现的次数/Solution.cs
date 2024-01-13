public class Solution {
    public int[] SingleNumbers(int[] nums) {
        int xs = 0;
        foreach(int x in nums) {
            xs ^= x;
        }
        int lb = xs & - xs;
        int a = 0;
        foreach(int x in nums) {
            if ((x & lb) != 0) {
                a ^= x;
            }
        }
        int b = xs ^ a;
        return new int[] {a, b};
    }
}

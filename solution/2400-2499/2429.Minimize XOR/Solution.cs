public class Solution {
    public int MinimizeXor(int num1, int num2) {
        int cnt = BitOperations.PopCount((uint)num2);
        int x = 0;
        for (int i = 30; i >= 0 && cnt > 0; --i) {
            if (((num1 >> i) & 1) == 1) {
                x |= 1 << i;
                --cnt;
            }
        }
        for (int i = 0; cnt > 0; ++i) {
            if (((num1 >> i) & 1) == 0) {
                x |= 1 << i;
                --cnt;
            }
        }
        return x;
    }
}

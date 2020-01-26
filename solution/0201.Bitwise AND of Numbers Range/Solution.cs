public class Solution {
    public int RangeBitwiseAnd(int m, int n) {
        var and = m & n;
        var xor = m ^ n;
        var temp = 0;
        while (xor > 0)
        {
            temp = (temp << 1) + 1;
            xor >>= 1;
        }
        return and & (~temp);
    }
}
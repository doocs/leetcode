public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 31; i++, n>>=1, res<<=1) {
            res |= (n&1);
        }
        res |= (n&1);
        return res;
    }
}
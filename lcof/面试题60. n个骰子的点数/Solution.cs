public class Solution {
    public double[] DicesProbability(int n) {
        var bp = new double[6];
        for (int i = 0; i < 6; i++) {
            bp[i] = 1 / 6.0;
        }
        double[] ans = new double[]{1};
        for (int i = 1; i <= n; i++) {
            var tmp = ans;
            ans = new double[tmp.Length + 5];
            for (int i1 = 0; i1 < tmp.Length; i1++) {
                for (int i2 = 0; i2 < bp.Length; i2++) {
                    ans[i1+i2] += tmp[i1] * bp[i2];
                }
            }
        }
        return ans;
    }
}
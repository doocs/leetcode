public class Solution {
    public IList<int> ReplaceNonCoprimes(int[] nums) {
        long Gcd(long a, long b) {
            while (b != 0) {
                long t = a % b;
                a = b;
                b = t;
            }
            return a;
        }

        var stk = new List<long>();
        foreach (int num in nums) {
            stk.Add(num);
            while (stk.Count > 1) {
                long x = stk[stk.Count - 1];
                long y = stk[stk.Count - 2];
                long g = Gcd(x, y);
                if (g == 1) {
                    break;
                }
                stk.RemoveAt(stk.Count - 1);
                stk[stk.Count - 1] = x / g * y;
            }
        }

        var ans = new List<int>();
        foreach (long v in stk) {
            ans.Add((int)v);
        }
        return ans;
    }
}

public class Solution {
    public long MaximumHappinessSum(int[] happiness, int k) {
        Array.Sort(happiness, (a, b) => b.CompareTo(a));
        long ans = 0;
        for (int i = 0; i < k; i++) {
            int x = happiness[i] - i;
            if (x <= 0) {
                break;
            }
            ans += x;
        }
        return ans;
    }
}

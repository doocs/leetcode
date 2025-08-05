public class Solution {
    public int TotalFruit(int[] fruits) {
        var cnt = new Dictionary<int, int>();
        int ans = 0;
        for (int i = 0, j = 0; i < fruits.Length; ++i) {
            int x = fruits[i];
            if (cnt.ContainsKey(x)) {
                cnt[x]++;
            } else {
                cnt[x] = 1;
            }
            while (cnt.Count > 2) {
                int y = fruits[j++];
                if (cnt.ContainsKey(y)) {
                    cnt[y]--;
                    if (cnt[y] == 0) {
                        cnt.Remove(y);
                    }
                }
            }
            ans = Math.Max(ans, i - j + 1);
        }
        return ans;
    }
}

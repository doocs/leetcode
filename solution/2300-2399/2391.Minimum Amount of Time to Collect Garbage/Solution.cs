public class Solution {
    public int GarbageCollection(string[] garbage, int[] travel) {
        Dictionary<char, int> last = new Dictionary<char, int>();
        int ans = 0;
        for (int i = 0; i < garbage.Length; ++i) {
            ans += garbage[i].Length;
            foreach (char c in garbage[i]) {
                last[c] = i;
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.Length; ++i) {
            ts += travel[i - 1];
            foreach (int j in last.Values) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
}
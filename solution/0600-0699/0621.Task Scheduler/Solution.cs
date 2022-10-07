public class Solution {
    public int LeastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int x = 0;
        foreach (char c in tasks) {
            cnt[c - 'A']++;
            x = Math.Max(x, cnt[c - 'A']);
        }
        int s = 0;
        foreach (int v in cnt) {
            s = v == x ? s + 1 : s;
        }
        return Math.Max(tasks.Length, (x - 1) * (n + 1) + s);
    }
}
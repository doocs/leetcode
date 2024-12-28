public class Solution {
    public string MinWindow(string s, string t) {
        int[] need = new int[128];
        int[] window = new int[128];

        foreach (var c in t) {
            need[c]++;
        }

        int m = s.Length, n = t.Length;
        int k = -1, mi = m + 1, cnt = 0;

        int l = 0;
        for (int r = 0; r < m; r++) {
            char c = s[r];
            window[c]++;

            if (window[c] <= need[c]) {
                cnt++;
            }

            while (cnt == n) {
                if (r - l + 1 < mi) {
                    mi = r - l + 1;
                    k = l;
                }

                c = s[l];
                if (window[c] <= need[c]) {
                    cnt--;
                }
                window[c]--;
                l++;
            }
        }

        return k < 0 ? "" : s.Substring(k, mi);
    }
}

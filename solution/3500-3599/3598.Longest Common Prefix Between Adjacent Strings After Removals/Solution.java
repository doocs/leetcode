class Solution {
    private final TreeMap<Integer, Integer> tm = new TreeMap<>();
    private String[] words;
    private int n;

    public int[] longestCommonPrefix(String[] words) {
        n = words.length;
        this.words = words;
        for (int i = 0; i + 1 < n; ++i) {
            tm.merge(calc(words[i], words[i + 1]), 1, Integer::sum);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            remove(i, i + 1);
            remove(i - 1, i);
            add(i - 1, i + 1);
            ans[i] = !tm.isEmpty() && tm.lastKey() > 0 ? tm.lastKey() : 0;
            remove(i - 1, i + 1);
            add(i - 1, i);
            add(i, i + 1);
        }
        return ans;
    }

    private void add(int i, int j) {
        if (i >= 0 && i < n && j >= 0 && j < n) {
            tm.merge(calc(words[i], words[j]), 1, Integer::sum);
        }
    }

    private void remove(int i, int j) {
        if (i >= 0 && i < n && j >= 0 && j < n) {
            int x = calc(words[i], words[j]);
            if (tm.merge(x, -1, Integer::sum) == 0) {
                tm.remove(x);
            }
        }
    }

    private int calc(String s, String t) {
        int m = Math.min(s.length(), t.length());
        for (int k = 0; k < m; ++k) {
            if (s.charAt(k) != t.charAt(k)) {
                return k;
            }
        }
        return m;
    }
}
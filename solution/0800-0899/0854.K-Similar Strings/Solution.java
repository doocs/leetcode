class Solution {
    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.offer(s1);
        vis.add(s1);
        int ans = 0;
        while (true) {
            for (int i = q.size(); i > 0; --i) {
                String s = q.pollFirst();
                if (s.equals(s2)) {
                    return ans;
                }
                for (String nxt : next(s, s2)) {
                    if (!vis.contains(nxt)) {
                        vis.add(nxt);
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
    }

    private List<String> next(String s, String s2) {
        int i = 0, n = s.length();
        char[] cs = s.toCharArray();
        for (; cs[i] == s2.charAt(i); ++i) {
        }

        List<String> res = new ArrayList<>();
        for (int j = i + 1; j < n; ++j) {
            if (cs[j] == s2.charAt(i) && cs[j] != s2.charAt(j)) {
                swap(cs, i, j);
                res.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return res;
    }

    private void swap(char[] cs, int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
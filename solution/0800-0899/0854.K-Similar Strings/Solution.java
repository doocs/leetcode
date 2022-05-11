class Solution {
    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> vis = new HashSet<>();
        q.offer(s1);
        vis.add(s1);
        int ans = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                s1 = q.poll();
                if (s1.equals(s2)) {
                    return ans;
                }
                for (String nxt : next(s1, s2)) {
                    if (!vis.contains(nxt)) {
                        vis.add(nxt);
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private List<String> next(String s, String s2) {
        int i = 0;
        int n = s.length();
        for (; i < n && s.charAt(i) == s2.charAt(i); ++i);
        char[] cs = s.toCharArray();
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
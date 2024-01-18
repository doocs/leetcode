class Solution {
    private int ans;
    private Set<String> s;
    private static final char[] seq = {'A', 'C', 'G', 'T'};

    public int minMutation(String start, String end, String[] bank) {
        s = new HashSet<>();
        for (String b : bank) {
            s.add(b);
        }
        ans = Integer.MAX_VALUE;
        dfs(start, end, 0);
        s.remove(start);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(String start, String end, int t) {
        if (start.equals(end)) {
            ans = Math.min(ans, t);
            return;
        }
        for (int i = 0; i < start.length(); ++i) {
            for (char c : seq) {
                if (start.charAt(i) == c) {
                    continue;
                }
                String nxt = start.substring(0, i) + c + start.substring(i + 1);
                if (s.contains(nxt)) {
                    s.remove(nxt);
                    dfs(nxt, end, t + 1);
                }
            }
        }
    }
}
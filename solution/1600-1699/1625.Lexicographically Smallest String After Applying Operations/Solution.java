class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> q = new ArrayDeque<>();
        q.offer(s);
        Set<String> vis = new HashSet<>();
        vis.add(s);
        String ans = s;
        while (!q.isEmpty()) {
            s = q.poll();
            if (s.compareTo(ans) < 0) {
                ans = s;
            }
            char[] cs = s.toCharArray();
            for (int i = 1; i < cs.length; i += 2) {
                cs[i] = (char) (((cs[i] - '0' + a) % 10) + '0');
            }
            String nxt1 = String.valueOf(cs);
            String nxt2 = s.substring(b) + s.substring(0, b);
            for (String nxt : new String[]{nxt1, nxt2}) {
                if (!vis.contains(nxt)) {
                    vis.add(nxt);
                    q.offer(nxt);
                }
            }
        }
        return ans;
    }
}
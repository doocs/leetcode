class Solution {
    public int minStickers(String[] stickers, String target) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0;
        int n = target.length();
        boolean[] vis = new boolean[1 << n];
        vis[0] = true;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                int state = q.poll();
                if (state == (1 << n) - 1) {
                    return ans;
                }
                for (String s : stickers) {
                    int nxt = state;
                    int[] cnt = new int[26];
                    for (char c : s.toCharArray()) {
                        ++cnt[c - 'a'];
                    }
                    for (int i = 0; i < n; ++i) {
                        int idx = target.charAt(i) - 'a';
                        if ((nxt & (1 << i)) == 0 && cnt[idx] > 0) {
                            nxt |= 1 << i;
                            --cnt[idx];
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.offer(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
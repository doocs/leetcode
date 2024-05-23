class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        boolean[] vis = new boolean[1 << n];
        vis[0] = true;
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int m = q.size(); m > 0; --m) {
                int cur = q.poll();
                if (cur == (1 << n) - 1) {
                    return ans;
                }
                for (String s : stickers) {
                    int[] cnt = new int[26];
                    int nxt = cur;
                    for (char c : s.toCharArray()) {
                        ++cnt[c - 'a'];
                    }
                    for (int i = 0; i < n; ++i) {
                        int j = target.charAt(i) - 'a';
                        if ((cur >> i & 1) == 0 && cnt[j] > 0) {
                            --cnt[j];
                            nxt |= 1 << i; 
                        }
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        q.offer(nxt);
                    }
                }
            }
        }
        return -1;
    }
}
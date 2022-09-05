class Solution {
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] ans = new int[m];
        Map<Character, int[]> mp = new HashMap<>(4);
        mp.put('L', new int[] {0, -1});
        mp.put('R', new int[] {0, 1});
        mp.put('U', new int[] {-1, 0});
        mp.put('D', new int[] {1, 0});
        for (int i = 0; i < m; ++i) {
            int x = startPos[0], y = startPos[1];
            int t = 0;
            for (int j = i; j < m; ++j) {
                char c = s.charAt(j);
                int a = mp.get(c)[0], b = mp.get(c)[1];
                if (0 <= x + a && x + a < n && 0 <= y + b && y + b < n) {
                    x += a;
                    y += b;
                    ++t;
                } else {
                    break;
                }
            }
            ans[i] = t;
        }
        return ans;
    }
}
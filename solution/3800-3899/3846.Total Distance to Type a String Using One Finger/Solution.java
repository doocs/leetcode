class Solution {
    private static final Map<Character, int[]> pos = new HashMap<>();

    static {
        String[] keys = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < keys[i].length(); j++) {
                pos.put(keys[i].charAt(j), new int[]{i, j});
            }
        }
    }

    public int totalDistance(String s) {
        char pre = 'a';
        int ans = 0;

        for (char cur : s.toCharArray()) {
            int[] p1 = pos.get(pre);
            int[] p2 = pos.get(cur);
            ans += Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
            pre = cur;
        }

        return ans;
    }
}
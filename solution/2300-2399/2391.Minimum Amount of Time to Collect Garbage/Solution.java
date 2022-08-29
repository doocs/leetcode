class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int ans = 0;
        int[] pos = new int[26];
        for (int i = 0; i < garbage.length; ++i) {
            ans += garbage[i].length();
            for (char c : garbage[i].toCharArray()) {
                pos[c - 'A'] = i;
            }
        }
        int[] s = new int[travel.length + 1];
        for (int i = 0; i < travel.length; ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : pos) {
            ans += s[i];
        }
        return ans;
    }
}
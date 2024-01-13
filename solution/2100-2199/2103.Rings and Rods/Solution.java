class Solution {
    public int countPoints(String rings) {
        int[] d = new int['Z'];
        d['R'] = 1;
        d['G'] = 2;
        d['B'] = 4;
        int[] mask = new int[10];
        for (int i = 0, n = rings.length(); i < n; i += 2) {
            int c = rings.charAt(i);
            int j = rings.charAt(i + 1) - '0';
            mask[j] |= d[c];
        }
        int ans = 0;
        for (int x : mask) {
            if (x == 7) {
                ++ans;
            }
        }
        return ans;
    }
}
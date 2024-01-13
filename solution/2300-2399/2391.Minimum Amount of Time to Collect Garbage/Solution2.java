class Solution {
    private String[] garbage;
    private int[] travel;

    public int garbageCollection(String[] garbage, int[] travel) {
        this.garbage = garbage;
        this.travel = travel;
        return f('M') + f('P') + f('G');
    }

    private int f(char c) {
        int ans = 0;
        int st = 0;
        for (int i = 0; i < garbage.length; ++i) {
            int cnt = 0;
            for (int j = 0; j < garbage[i].length(); ++j) {
                if (garbage[i].charAt(j) == c) {
                    ++cnt;
                }
            }
            if (cnt > 0) {
                ans += cnt + st;
                st = 0;
            }
            if (i < travel.length) {
                st += travel[i];
            }
        }
        return ans;
    }
}
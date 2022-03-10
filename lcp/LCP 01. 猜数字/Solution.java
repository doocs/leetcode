class Solution {
    public int game(int[] guess, int[] answer) {
        int ans = 0;
        for (int i = 0; i < 3; ++i) {
            ans += guess[i] == answer[i] ? 1 : 0;
        }
        return ans;
    }
}
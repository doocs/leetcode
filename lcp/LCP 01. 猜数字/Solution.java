class Solution {
    public int game(int[] guess, int[] answer) {
        int times = 0;
        for (int i = 0; i < 3; ++i) {
            times += (guess[i] == answer[i] ? 1 : 0);
        }
        return times;
    }
}
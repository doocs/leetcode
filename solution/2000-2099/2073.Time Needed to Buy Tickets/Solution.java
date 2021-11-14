class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        for (int i = 0, ans = 1;; i = (i + 1) % tickets.length) {
            if (i == k && tickets[i] == 1) {
                return ans;
            }
            if (tickets[i] > 0) {
                --tickets[i];
                ++ans;
            }
        }
    }
}
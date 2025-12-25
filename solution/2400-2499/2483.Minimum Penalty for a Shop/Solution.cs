public class Solution {
    public int BestClosingTime(string customers) {
        int n = customers.Length;
        int ans = 0, cost = 0;
        for (int i = 0; i < n; i++) {
            if (customers[i] == 'Y') {
                cost++;
            }
        }
        int mn = cost;
        for (int j = 1; j <= n; j++) {
            cost += customers[j - 1] == 'N' ? 1 : -1;
            if (cost < mn) {
                ans = j;
                mn = cost;
            }
        }
        return ans;
    }
}

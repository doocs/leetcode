class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        for (int x = 0; x <= total / cost1; ++x) {
            int v = total - x * cost1;
            ans += v / cost2 + 1;
        }
        return ans;
    }
}
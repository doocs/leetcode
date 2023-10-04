class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0, s = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            s += satisfaction[i];
            if (s <= 0) {
                break;
            }
            ans += s;
        }
        return ans;
    }
}
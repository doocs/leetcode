class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int cur = 1;
        for (int t : target) {
            for (int i = cur; i <= n; ++i) {
                ans.add("Push");
                if (t == i) {
                    cur = i + 1;
                    break;
                }
                ans.add("Pop");
            }
        }
        return ans;
    }
}
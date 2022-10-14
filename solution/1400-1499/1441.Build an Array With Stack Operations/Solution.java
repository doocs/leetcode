class Solution {
    public List<String> buildArray(int[] target, int n) {
        int cur = 0;
        List<String> ans = new ArrayList<>();
        for (int v : target) {
            while (++cur < v) {
                ans.add("Push");
                ans.add("Pop");
            }
            ans.add("Push");
        }
        return ans;
    }
}
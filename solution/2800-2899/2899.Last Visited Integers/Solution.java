class Solution {
    public List<Integer> lastVisitedIntegers(int[] nums) {
        List<Integer> seen = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        for (int x : nums) {
            if (x == -1) {
                if (++k > seen.size()) {
                    ans.add(-1);
                } else {
                    ans.add(seen.get(seen.size() - k));
                }
            } else {
                k = 0;
                seen.add(x);
            }
        }
        return ans;
    }
}

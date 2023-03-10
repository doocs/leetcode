class Solution {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int x : nums) {
            int y = target - x;
            if (cnt.containsKey(y)) {
                ans.add(List.of(x, y));
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            } else {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return ans;
    }
}
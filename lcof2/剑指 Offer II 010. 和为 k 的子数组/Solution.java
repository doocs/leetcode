class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0, sum = 0;
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            ans += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return ans;
    }
}
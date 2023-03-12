class Solution {
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int mask = 0;
        for (int x : nums) {
            mask ^= x;
            ans += cnt.getOrDefault(mask, 0);
            cnt.merge(mask, 1, Integer::sum);
        }
        return ans;
    }
}
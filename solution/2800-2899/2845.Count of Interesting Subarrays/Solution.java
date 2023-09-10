class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nums.get(i) % modulo == k ? 1 : 0;
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int s = 0;
        for (int x : arr) {
            s += x;
            ans += cnt.getOrDefault((s - k + modulo) % modulo, 0);
            cnt.merge(s % modulo, 1, Integer::sum);
        }
        return ans;
    }
}
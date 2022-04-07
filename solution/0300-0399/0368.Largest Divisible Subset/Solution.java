class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n], p = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 1, pre = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] + 1 > l) {
                    l = f[j] + 1;
                    pre = j;
                }
            }
            f[i] = l;
            p[i] = pre;
        }
        int maxLen = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > maxLen) {
                maxLen = f[i];
                maxIndex = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < maxLen) {
            ans.add(nums[maxIndex]);
            maxIndex = p[maxIndex];
        }
        Collections.reverse(ans);
        return ans;
    }
}
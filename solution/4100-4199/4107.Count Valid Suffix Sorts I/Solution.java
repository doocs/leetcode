class Solution {
    public int countSuffix(int[] nums, int[] target) {
        Map<Integer, Integer> diff = new HashMap<>();
        for (int x : nums) {
            diff.merge(x, 1, Integer::sum);
        }
        for (int x : target) {
            diff.merge(x, -1, Integer::sum);
            if (diff.get(x) < 0) {
                return 0;
            }
        }

        int n = nums.length;
        int maxI = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                maxI = i;
                break;
            }
        }

        int minI = 0;
        for (int i = n - 1; i > 0; i--) {
            if (target[i - 1] < target[i]) {
                minI = i;
                break;
            }
        }

        return Math.max(maxI - minI + 1, 0);
    }
}
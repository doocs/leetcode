class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int bestAns = 100000;
        for (int i = 0;i + 2 < nums.length;i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(target - bestAns) > Math.abs(target - sum)) {
                    bestAns = sum;
                }
                if (sum > target) {
                    int k0 = k - 1;
                    while (j < k0 && nums[k] == nums[k0]) {
                        k0--;
                    }
                    if (j == k0) {
                        break;
                    } else {
                        k = k0;
                    }
                } else {
                    int j0 = j + 1;
                    while (j0 < j && nums[j] == nums[j0]) {
                        j0++;
                    }
                    if (j0 == k) {
                        break;
                    } else {
                        j = j0;
                    }
                }
            }
        }
        return bestAns;
    }
}
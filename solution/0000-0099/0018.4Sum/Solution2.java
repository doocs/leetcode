class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int low = j + 1, high = nums.length - 1;
                int sub = target - nums[i] - nums[j];
                while (low < high) {
                    if (nums[low] + nums[high] < sub) {
                        low++;
                    } else if (nums[low] + nums[high] > sub) {
                        high--;
                    } else {
                        results.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        low++;
                        high--;
                    }
                }
            }
        }

        return results.stream().distinct().collect(Collectors.toList());
    }
}
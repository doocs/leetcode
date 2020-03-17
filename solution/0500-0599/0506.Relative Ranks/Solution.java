class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int n = nums.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }
        Arrays.sort(index, (o1, o2) -> Integer.compare(nums[o2], nums[o1]));
        String[] res = new String[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                res[index[i]] = "Gold Medal";
            } else if (i == 1) {
                res[index[i]] = "Silver Medal";
            } else if (i == 2) {
                res[index[i]] = "Bronze Medal";
            } else {
                res[index[i]] = String.valueOf(i + 1);
            }
        }
        return res;
    }
}

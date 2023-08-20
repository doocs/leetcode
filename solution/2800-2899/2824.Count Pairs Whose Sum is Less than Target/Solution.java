class Solution {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int ans = 0;
        for (int j = 0; j < nums.size(); ++j) {
            int x = nums.get(j);
            int i = search(nums, target - x, j);
            ans += i;
        }
        return ans;
    }

    private int search(List<Integer> nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
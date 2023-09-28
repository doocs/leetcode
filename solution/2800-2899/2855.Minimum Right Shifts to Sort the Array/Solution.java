class Solution {
    public int minimumRightShifts(List<Integer> nums) {
        int n = nums.size();
        int i = 1;
        while (i < n && nums.get(i - 1) < nums.get(i)) {
            ++i;
        }
        int k = i + 1;
        while (k < n && nums.get(k - 1) < nums.get(k) && nums.get(k) < nums.get(0)) {
            ++k;
        }
        return k < n ? -1 : n - i;
    }
}
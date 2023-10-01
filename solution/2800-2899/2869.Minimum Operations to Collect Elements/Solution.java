class Solution {
    public int minOperations(List<Integer> nums, int k) {
        boolean[] isAdded = new boolean[k];
        int n = nums.size();
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums.get(i) > k || isAdded[nums.get(i) - 1]) {
                continue;
            }
            isAdded[nums.get(i) - 1] = true;
            count++;
            if (count == k) {
                return n - i;
            }
        }
        return n;
    }
}

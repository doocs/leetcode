class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 1, j = numbers.length;
        while (i < j) {
            int x = numbers[i - 1] + numbers[j - 1];
            if (x == target) {
                return new int[]{i, j};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
        return new int[]{-1, -1};
    }
}
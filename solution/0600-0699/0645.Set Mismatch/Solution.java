class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int s1 = (1 + n) * n / 2;
        int s2 = 0;
        Set<Integer> set = new HashSet<>();
        int s = 0;
        for (int x : nums) {
            if (set.add(x)) {
                s2 += x;
            }
            s += x;
        }
        return new int[] {s - s2, s1 - s2};
    }
}
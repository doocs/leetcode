class Solution {
    public int[] exchange(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int p = 0, q = len - 1;
        for (int e : nums) {
            if ((e & 1) == 0) {
                res[q--] = e;
            } else {
                res[p++] = e;
            }
        }
        return res;
    }
}
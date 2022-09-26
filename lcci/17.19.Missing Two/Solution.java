class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int xor = 0;
        for (int v : nums) {
            xor ^= v;
        }
        for (int i = 1; i <= n; ++i) {
            xor ^= i;
        }
        int diff = xor & (-xor);
        int a = 0;
        for (int v : nums) {
            if ((v & diff) != 0) {
                a ^= v;
            }
        }
        for (int i = 1; i <= n; ++i) {
            if ((i & diff) != 0) {
                a ^= i;
            }
        }
        int b = xor ^ a;
        return new int[] {a, b};
    }
}
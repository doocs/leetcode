class Solution {
    private int[] nums;
    private int n;

    public int minimumOperations(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int[] e1 : get(0)) {
            for (int[] e2 : get(1)) {
                if (e1[0] != e2[0]) {
                    ans = Math.min(ans, n - (e1[1] + e2[1]));
                }
            }
        }
        return ans;
    }

    private int[][] get(int i) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (; i < n; i += 2) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        int a = 0;
        int n1 = 0;
        int b = 0;
        int n2 = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int k = e.getKey();
            int v = e.getValue();
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return new int[][] {{a, n1}, {b, n2}};
    }
}
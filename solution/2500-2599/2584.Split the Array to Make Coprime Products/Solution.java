class Solution {
    public int findValidSplit(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        int n = nums.length;
        int[] last = new int[n];
        for (int i = 0; i < n; ++i) {
            last[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            for (int j = 2; j <= x / j; ++j) {
                if (x % j == 0) {
                    if (first.containsKey(j)) {
                        last[first.get(j)] = i;
                    } else {
                        first.put(j, i);
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (first.containsKey(x)) {
                    last[first.get(x)] = i;
                } else {
                    first.put(x, i);
                }
            }
        }
        int mx = last[0];
        for (int i = 0; i < n; ++i) {
            if (mx < i) {
                return mx;
            }
            mx = Math.max(mx, last[i]);
        }
        return -1;
    }
}
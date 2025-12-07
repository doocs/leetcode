class Solution {
    private static final List<Integer> primes = new ArrayList<>();

    static {
        int N = 1 << 14;
        for (int i = 0; i < N; i++) {
            String s = Integer.toBinaryString(i);
            String rs = new StringBuilder(s).reverse().toString();
            if (s.equals(rs)) {
                primes.add(i);
            }
        }
    }

    public int[] minOperations(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int k = 0; k < n; ++k) {
            int x = nums[k];
            int i = binarySearch(primes, x);
            if (i < primes.size()) {
                ans[k] = Math.min(ans[k], primes.get(i) - x);
            }
            if (i >= 1) {
                ans[k] = Math.min(ans[k], x - primes.get(i - 1));
            }
        }

        return ans;
    }

    private int binarySearch(List<Integer> primes, int x) {
        int l = 0, r = primes.size();
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (primes.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

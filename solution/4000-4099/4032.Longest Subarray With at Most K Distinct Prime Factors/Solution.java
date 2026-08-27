class Solution {
    static final int MX = 100001;
    static List<Integer>[] primes = new ArrayList[MX];

    static {
        for (int i = 0; i < MX; i++) {
            primes[i] = new ArrayList<>();
        }

        for (int i = 2; i < MX; i++) {
            if (primes[i].isEmpty()) {
                for (int j = i; j < MX; j += i) {
                    primes[j].add(i);
                }
            }
        }
    }

    public int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();

        int ans = 0;
        int l = 0;

        for (int r = 0; r < nums.length; r++) {
            for (int p : primes[nums[r]]) {
                cnt.merge(p, 1, Integer::sum);
            }

            while (cnt.size() > k) {
                for (int p : primes[nums[l]]) {
                    if (cnt.merge(p, -1, Integer::sum) == 0) {
                        cnt.remove(p);
                    }
                }
                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
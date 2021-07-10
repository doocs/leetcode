class Solution {

    private static final int MOD = 1000000007;

    public int countPairs(int[] deliciousness) {
        int limit = Arrays.stream(deliciousness).max().getAsInt() * 2;
        int pairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : deliciousness) {
            for (int sum = 1; sum <= limit; sum <<= 1) {
                int count = freq.getOrDefault(sum - d, 0);
                pairs = (pairs + count) % MOD;
            }
            freq.merge(d, 1, Integer::sum);
        }
        return pairs;
    }
}

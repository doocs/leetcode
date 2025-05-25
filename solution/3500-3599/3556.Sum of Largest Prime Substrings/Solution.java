class Solution {
    public long sumOfLargestPrimes(String s) {
        Set<Long> st = new HashSet<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            long x = 0;
            for (int j = i; j < n; j++) {
                x = x * 10 + (s.charAt(j) - '0');
                if (is_prime(x)) {
                    st.add(x);
                }
            }
        }

        List<Long> sorted = new ArrayList<>(st);
        Collections.sort(sorted);

        long ans = 0;
        int start = Math.max(0, sorted.size() - 3);
        for (int idx = start; idx < sorted.size(); idx++) {
            ans += sorted.get(idx);
        }
        return ans;
    }

    private boolean is_prime(long x) {
        if (x < 2) return false;
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
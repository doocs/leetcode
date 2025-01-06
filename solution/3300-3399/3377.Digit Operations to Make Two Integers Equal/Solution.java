class Solution {
    private boolean[] sieve;

    private void runSieve() {
        sieve = new boolean[100000];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;
        for (int i = 2; i < 100000; i++) {
            if (sieve[i]) {
                for (int j = 2 * i; j < 100000; j += i) {
                    sieve[j] = false;
                }
            }
        }
    }

    private int solve(int n, int m) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {n, n});
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int sum = top[0], cur = top[1];

            if (visited.contains(cur)) {
                continue;
            }
            visited.add(cur);

            if (cur == m) {
                return sum;
            }

            char[] s = String.valueOf(cur).toCharArray();
            for (int i = 0; i < s.length; i++) {
                char c = s[i];

                if (s[i] < '9') {
                    s[i] = (char) (s[i] + 1);
                    int next = Integer.parseInt(new String(s));
                    if (!sieve[next] && !visited.contains(next)) {
                        pq.add(new int[] {sum + next, next});
                    }
                    s[i] = c;
                }

                if (s[i] > '0' && !(i == 0 && s[i] == '1')) {
                    s[i] = (char) (s[i] - 1);
                    int next = Integer.parseInt(new String(s));
                    if (!sieve[next] && !visited.contains(next)) {
                        pq.add(new int[] {sum + next, next});
                    }
                    s[i] = c;
                }
            }
        }

        return -1;
    }

    public int minOperations(int n, int m) {
        runSieve();
        if (sieve[n] || sieve[m]) {
            return -1;
        }
        return solve(n, m);
    }
}
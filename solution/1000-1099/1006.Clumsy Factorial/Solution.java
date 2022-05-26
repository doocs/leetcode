class Solution {
    public int clumsy(int N) {
        Deque<Integer> s = new ArrayDeque<>();
        s.offerLast(N);
        int op = 0;
        for (int i = N - 1; i > 0; --i) {
            if (op == 0) {
                s.offerLast(s.pollLast() * i);
            } else if (op == 1) {
                s.offerLast(s.pollLast() / i);
            } else if (op == 2) {
                s.offerLast(i);
            } else {
                s.offerLast(-i);
            }
            op = (op + 1) % 4;
        }
        int res = 0;
        while (!s.isEmpty()) {
            res += s.pollLast();
        }
        return res;
    }
}
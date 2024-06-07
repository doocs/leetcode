class Solution {
    public int clumsy(int n) {
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(n);
        int k = 0;
        for (int x = n - 1; x > 0; --x) {
            if (k == 0) {
                stk.push(stk.pop() * x);
            } else if (k == 1) {
                stk.push(stk.pop() / x);
            } else if (k == 2) {
                stk.push(x);
            } else {
                stk.push(-x);
            }
            k = (k + 1) % 4;
        }
        return stk.stream().mapToInt(Integer::intValue).sum();
    }
}
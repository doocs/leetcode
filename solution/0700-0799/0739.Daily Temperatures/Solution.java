class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!s.isEmpty() && T[s.peek()] < T[i]) {
                int j = s.pop();
                res[j] = i - j;
            }
            s.push(i);
        }
        return res;
    }
}
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        int curr = -1;
        for (String log : logs) {
            String[] t = log.split(":");
            int fid = Integer.parseInt(t[0]);
            int ts = Integer.parseInt(t[2]);
            if ("start".equals(t[1])) {
                if (!stk.isEmpty()) {
                    ans[stk.peek()] += ts - curr;
                }
                stk.push(fid);
                curr = ts;
            } else {
                fid = stk.pop();
                ans[fid] += ts - curr + 1;
                curr = ts + 1;
            }
        }
        return ans;
    }
}
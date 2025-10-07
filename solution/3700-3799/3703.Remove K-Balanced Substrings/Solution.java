class Solution {
    public String removeSubstring(String s, int k) {
        List<int[]> stk = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (!stk.isEmpty() && stk.get(stk.size() - 1)[0] == c) {
                stk.get(stk.size() - 1)[1] += 1;
            } else {
                stk.add(new int[] {c, 1});
            }
            if (c == ')' && stk.size() > 1) {
                int[] top = stk.get(stk.size() - 1);
                int[] prev = stk.get(stk.size() - 2);
                if (top[1] == k && prev[1] >= k) {
                    stk.remove(stk.size() - 1);
                    prev[1] -= k;
                    if (prev[1] == 0) {
                        stk.remove(stk.size() - 1);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] pair : stk) {
            for (int i = 0; i < pair[1]; i++) {
                sb.append((char) pair[0]);
            }
        }
        return sb.toString();
    }
}

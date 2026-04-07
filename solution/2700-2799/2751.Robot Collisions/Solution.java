class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (a, b) -> positions[a] - positions[b]);

        Deque<Integer> stk = new ArrayDeque<>();

        for (int i : idx) {
            if (directions.charAt(i) == 'R') {
                stk.push(i);
                continue;
            }

            while (!stk.isEmpty() && healths[i] > 0) {
                int j = stk.peek();

                if (healths[j] > healths[i]) {
                    healths[j]--;
                    healths[i] = 0;
                } else if (healths[j] < healths[i]) {
                    healths[i]--;
                    healths[j] = 0;
                    stk.pop();
                } else {
                    healths[i] = healths[j] = 0;
                    stk.pop();
                    break;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                ans.add(h);
            }
        }
        return ans;
    }
}

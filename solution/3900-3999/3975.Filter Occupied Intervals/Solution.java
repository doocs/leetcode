class Solution {
    public List<List<Integer>> filterOccupiedIntervals(
        int[][] occupiedIntervals, int freeStart, int freeEnd) {
        Arrays.sort(occupiedIntervals, (a, b) -> a[0] - b[0]);

        List<int[]> busy = new ArrayList<>();
        busy.add(occupiedIntervals[0]);

        for (int i = 1; i < occupiedIntervals.length; i++) {
            int[] cur = occupiedIntervals[i];
            int[] last = busy.get(busy.size() - 1);

            if (last[1] + 1 < cur[0]) {
                busy.add(cur);
            } else {
                last[1] = Math.max(last[1], cur[1]);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int[] interval : busy) {
            int s = interval[0], e = interval[1];

            if (e < freeStart || s > freeEnd) {
                ans.add(Arrays.asList(s, e));
            } else {
                if (s < freeStart) {
                    ans.add(Arrays.asList(s, freeStart - 1));
                }
                if (e > freeEnd) {
                    ans.add(Arrays.asList(freeEnd + 1, e));
                }
            }
        }

        return ans;
    }
}
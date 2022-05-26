class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> colorIndexes = new HashMap<>();
        for (int i = 0; i < colors.length; ++i) {
            int c = colors[i];
            colorIndexes.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0], c = query[1];
            if (!colorIndexes.containsKey(c)) {
                res.add(-1);
                continue;
            }
            List<Integer> t = colorIndexes.get(c);
            int left = 0, right = t.size() - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (t.get(mid) >= i) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int val = Math.abs(t.get(left) - i);
            if (left > 0) {
                val = Math.min(val, Math.abs(t.get(left - 1) - i));
            }
            if (left < t.size() - 1) {
                val = Math.min(val, Math.abs(t.get(left + 1) - i));
            }
            res.add(val);
        }
        return res;
    }
}
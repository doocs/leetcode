class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> v = new ArrayList<>();
        int x = location.get(0), y = location.get(1);
        int same = 0;
        for (List<Integer> p : points) {
            int xi = p.get(0), yi = p.get(1);
            if (xi == x && yi == y) {
                ++same;
                continue;
            }
            v.add(Math.atan2(yi - y, xi - x));
        }
        Collections.sort(v);
        int n = v.size();
        for (int i = 0; i < n; ++i) {
            v.add(v.get(i) + 2 * Math.PI);
        }
        int mx = 0;
        Double t = angle * Math.PI / 180;
        for (int i = 0, j = 0; j < 2 * n; ++j) {
            while (i < j && v.get(j) - v.get(i) > t) {
                ++i;
            }
            mx = Math.max(mx, j - i + 1);
        }
        return mx + same;
    }
}
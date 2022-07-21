class Solution {
    public boolean canChange(String start, String target) {
        List<int[]> a = f(start);
        List<int[]> b = f(target);
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            int[] x = a.get(i);
            int[] y = b.get(i);
            if (x[0] != y[0]) {
                return false;
            }
            if (x[0] == 1 && x[1] < y[1]) {
                return false;
            }
            if (x[0] == 2 && x[1] > y[1]) {
                return false;
            }
        }
        return true;
    }

    private List<int[]> f(String s) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'L') {
                res.add(new int[]{1, i});
            } else if (s.charAt(i) == 'R') {
                res.add(new int[]{2, i});
            }
        }
        return res;
    }
}
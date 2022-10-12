class Solution {
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < n - 1; ++i) {
            for (String x : f(s, 1, i)) {
                for (String y : f(s, i, n - 1)) {
                    ans.add(String.format("(%s, %s)", x, y));
                }
            }
        }
        return ans;
    }

    private List<String> f(String s, int i, int j) {
        List<String> res = new ArrayList<>();
        for (int k = 1; k <= j - i; ++k) {
            String l = s.substring(i, i + k);
            String r = s.substring(i + k, j);
            boolean ok = ("0".equals(l) || !l.startsWith("0")) && !r.endsWith("0");
            if (ok) {
                res.add(l + (k < j - i ? "." : "") + r);
            }
        }
        return res;
    }
}
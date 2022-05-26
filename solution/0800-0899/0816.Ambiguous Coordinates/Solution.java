class Solution {
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i = 2; i < n - 1; ++i) {
            for (String x : convert(s, 1, i)) {
                for (String y : convert(s, i, n - 1)) {
                    ans.add(String.format("(%s, %s)", x, y));
                }
            }
        }
        return ans;
    }

    private List<String> convert(String s, int i, int j) {
        List<String> res = new ArrayList<>();
        for (int k = 1; k <= j - i; ++k) {
            String left = s.substring(i, i + k);
            String right = s.substring(i + k, j);
            boolean valid = ("0".equals(left) || !left.startsWith("0")) && !right.endsWith("0");
            if (valid) {
                res.add(left + (k < j - i ? "." : "") + right);
            }
        }
        return res;
    }
}

class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int n = 0;
        for (var w : words) {
            n = Math.max(n, w.length());
        }
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            StringBuilder t = new StringBuilder();
            for (var w : words) {
                t.append(j < w.length() ? w.charAt(j) : ' ');
            }
            while (t.length() > 0 && t.charAt(t.length() - 1) == ' ') {
                t.deleteCharAt(t.length() - 1);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
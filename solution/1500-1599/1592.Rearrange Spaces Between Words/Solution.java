class Solution {
    public String reorderSpaces(String text) {
        int cnt = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                ++cnt;
            }
        }
        String[] words = text.split("\\s+");
        List<String> res = new ArrayList<>();
        for (String w : words) {
            if (!"".equals(w)) {
                res.add(w);
            }
        }
        int m = res.size() - 1;
        if (m == 0) {
            return res.get(0) + " ".repeat(cnt);
        }
        String ans = String.join(" ".repeat(cnt / m), res);
        ans += " ".repeat(cnt % m);
        return ans;
    }
}
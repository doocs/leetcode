class Solution {
    public String makeLargestSpecial(String s) {
        if ("".equals(s)) {
            return "";
        }
        List<String> ans = new ArrayList<>();
        int cnt = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            cnt += s.charAt(i) == '1' ? 1 : -1;
            if (cnt == 0) {
                String t = "1" + makeLargestSpecial(s.substring(j + 1, i)) + "0";
                ans.add(t);
                j = i + 1;
            }
        }
        ans.sort(Comparator.reverseOrder());
        return String.join("", ans);
    }
}
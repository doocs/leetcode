class Solution {
    public String oddString(String[] words) {
        var d = new HashMap<String, List<String>>();
        for (var s : words) {
            int m = s.length();
            var cs = new char[m - 1];
            for (int i = 0; i < m - 1; ++i) {
                cs[i] = (char) (s.charAt(i + 1) - s.charAt(i));
            }
            var t = String.valueOf(cs);
            d.putIfAbsent(t, new ArrayList<>());
            d.get(t).add(s);
        }
        for (var ss : d.values()) {
            if (ss.size() == 1) {
                return ss.get(0);
            }
        }
        return "";
    }
}
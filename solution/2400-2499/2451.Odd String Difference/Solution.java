class Solution {
    public String oddString(String[] words) {
        Map<String, List<String>> cnt = new HashMap<>();
        for (var w : words) {
            List<String> d = new ArrayList<>();
            for (int i = 0; i < w.length() - 1; ++i) {
                d.add(String.valueOf(w.charAt(i + 1) - w.charAt(i)));
            }
            cnt.computeIfAbsent(String.join(",", d), k -> new ArrayList<>()).add(w);
        }
        for (var v : cnt.values()) {
            if (v.size() == 1) {
                return v.get(0);
            }
        }
        return "";
    }
}
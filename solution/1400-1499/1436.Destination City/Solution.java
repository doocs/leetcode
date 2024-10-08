class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> s = new HashSet<>();
        for (var p : paths) {
            s.add(p.get(0));
        }
        for (int i = 0;; ++i) {
            var b = paths.get(i).get(1);
            if (!s.contains(b)) {
                return b;
            }
        }
    }
}

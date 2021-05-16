class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = 10;
        Set<String> subs = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < s.length() - len + 1; ++i) {
            String sub = s.substring(i, i + len);
            if (subs.contains(sub)) {
                res.add(sub);
            }
            subs.add(sub);
        }
        return new ArrayList<>(res);
    }
}
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> mp = new HashMap<>();
        for (String s : strings) {
            int diff = s.charAt(0) - 'a';
            char[] t = s.toCharArray();
            for (int i = 0; i < t.length; ++i) {
                char d = (char) (t[i] - diff);
                if (d < 'a') {
                    d += 26;
                }
                t[i] = d;
            }
            String key = new String(t);
            mp.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(mp.values());
    }
}
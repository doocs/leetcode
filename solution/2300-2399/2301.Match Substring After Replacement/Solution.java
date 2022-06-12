class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        Map<Character, Set<Character>> d = new HashMap<>();
        for (char[] m : mappings) {
            d.computeIfAbsent(m[0], k -> new HashSet<>()).add(m[1]);
        }
        int n = s.length(), k = sub.length();
        for (int i = 0; i <= n - k; ++i) {
            boolean flag = true;
            for (int j = 0; j < k; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a == b || d.getOrDefault(b, Collections.emptySet()).contains(a)) {
                    continue;
                }
                flag = false;
                break;
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
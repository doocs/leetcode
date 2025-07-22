class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> s = new HashSet<>();
        for (var w : bannedWords) {
            s.add(w);
        }
        int cnt = 0;
        for (var w : message) {
            if (s.contains(w) && ++cnt >= 2) {
                return true;
            }
        }
        return false;
    }
}

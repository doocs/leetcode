class Solution {
    public boolean hasMatch(String s, String p) {
        int i = 0;
        for (String t : p.split("\\*")) {
            int j = s.indexOf(t, i);
            if (j == -1) {
                return false;
            }
            i = j + t.length();
        }
        return true;
    }
}

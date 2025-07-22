class Solution {
    public int minTimeToType(String word) {
        int ans = word.length();
        char a = 'a';
        for (char c : word.toCharArray()) {
            int d = Math.abs(a - c);
            ans += Math.min(d, 26 - d);
            a = c;
        }
        return ans;
    }
}

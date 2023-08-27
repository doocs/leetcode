class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        return Math.abs(count(moves, 'L') - count(moves, 'R')) + count(moves, '_');
    }

    private int count(String s, char c) {
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++cnt;
            }
        }
        return cnt;
    }
}
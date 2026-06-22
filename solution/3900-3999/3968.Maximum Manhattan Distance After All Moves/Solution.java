class Solution {
    public int maxDistance(String moves) {
        int x = 0, y = 0, z = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') {
                x -= 1;
            } else if (c == 'D') {
                x += 1;
            } else if (c == 'L') {
                y -= 1;
            } else if (c == 'R') {
                y += 1;
            } else {
                z += 1;
            }
        }
        return Math.abs(x) + Math.abs(y) + z;
    }
}
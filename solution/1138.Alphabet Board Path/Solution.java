class Solution {
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int x = 0, y = 0;
        for (char c : target.toCharArray()) {
            int dx = (c - 'a') / 5;
            int dy = (c - 'a') % 5;
            if (dy < y) {
                int n = y - dy;
                while (n-- > 0) {
                    sb.append('L');
                }
            }
            if (dx > x) {
                int n = dx - x;
                while (n-- > 0) {
                    sb.append('D');
                }
            }
            if (dx < x) {
                int n = x - dx;
                while (n-- > 0) {
                    sb.append('U');
                }
            }
            if (dy > y) {
                int n = dy - y;
                while (n-- > 0) {
                    sb.append('R');
                }
            }
            sb.append('!');
            x = dx;
            y = dy;
        }
        return sb.toString();
    }
}

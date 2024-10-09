class Solution {
    public int finalPositionOfSnake(int n, List<String> commands) {
        int x = 0, y = 0;
        for (var c : commands) {
            switch (c.charAt(0)) {
                case 'U' -> x--;
                case 'D' -> x++;
                case 'L' -> y--;
                case 'R' -> y++;
            }
        }
        return x * n + y;
    }
}

class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] direction = new int[4];
        int cur = 0;
        char[] chars = instructions.toCharArray();
        for (char c : chars) {
            if (c == 'L') {
                cur = cur < 3 ? cur + 1 : 0;
            } else if (c == 'R') {
                cur = cur > 0 ? cur - 1 : 3;
            } else {
                direction[cur] += 1;
            }
        }
        return cur != 0 || (direction[0] == direction[2] && direction[1] == direction[3]);
    }
}
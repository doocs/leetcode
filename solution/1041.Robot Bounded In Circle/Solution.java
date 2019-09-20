class Solution {
    public boolean isRobotBounded(String instructions) {
        int col = 0;
        int row = 0;
        char[] orders = instructions.toCharArray();
        int order = 0;
        for (int i = 0; i < 4; i++) {
            for (char ch : orders) {
                if (ch == 'L') {
                    order--;
                    if (order == -3) {
                        order = 1;
                    }
                } else if (ch == 'R') {
                    order++;
                    if (order == 2) {
                        order = -2;
                    }
                } else {
                    switch (order) {
                        case 0:
                            row++;
                            break;
                        case 1:
                            col++;
                            break;
                        case -1:
                            col--;
                            break;
                        case -2:
                            row--;
                            break;
                        default:
                            break;
                    }
                }
            }
            if (col == 0 && row == 0) {
                return true;
            }
        }

        return false;
    }
}

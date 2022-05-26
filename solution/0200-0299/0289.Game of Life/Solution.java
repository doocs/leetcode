class Solution {
    public void gameOfLife(int[][] board) {
		final int m = board.length;
		final int n = board[0].length;
        
        /**
            状态0：死细胞转为死细胞
            状态1：活细胞转为活细胞
            状态2：活细胞转为死细胞
            状态3：死细胞转为活细胞
        **/
        
		// encode
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				int live = countLive(board, i, j); // number of live cells
				if (board[i][j] == 0 && live == 3) {
					board[i][j] = 3;
				} else if (board[i][j] == 1 && (live < 2 || live > 3)) {
					board[i][j] = 2;
				}
			}
		}
		// decode
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				board[i][j] %= 2;
			}
		}
	}
	
	private int countLive(int[][] nums, int i, int j) {
        int count = 0;
        int m = nums.length, n = nums[0].length;
        for(int x = i - 1; x <= i + 1; x++) {
            for(int y = j - 1; y <= j + 1; y++) {
                if(x == i && y == j) continue;
                if(x >= 0 && x < m && y >= 0 && y < n && (nums[x][y] == 1 || nums[x][y] == 2)) {
                    count++;
                }
            }
        }
        return count;
    }
}
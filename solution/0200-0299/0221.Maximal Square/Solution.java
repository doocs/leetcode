public class Solution {
    public int maximalSquare(char[][] matrix) {

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int mx = matrix.length;
		int my = matrix[0].length;

		int[][] dp = new int[mx][my];
		int max = 0;

		// 初始化第0行
		for (int i = 0; i < my; i++) {
			if (matrix[0][i] == '1') {
				dp[0][i] = 1;
				max = 1;
			}
		}

		// 初始化第0列
		for (int i = 1; i < mx; i++) {
			if (matrix[i][0] == '1') {
				dp[i][0] = 1;
				max = 1;
			}
		}

		// dp[x][y]=min(dp[x-1][y],dp[x][y-1],dp[x-1][y-1])+1
		for (int x = 1; x < mx; x++) {
			for (int y = 1; y < my; y++) {

				if (matrix[x][y] == '1') {
					dp[x][y] = Math.min(Math.min(dp[x - 1][y], dp[x][y - 1]),
							dp[x - 1][y - 1]) + 1;
					max = Math.max(max, dp[x][y]);
				}

			}
		}

		return max * max;
	
        
    }
}
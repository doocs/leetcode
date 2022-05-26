class Solution {
    
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] f = new int[n + 2][n + 2];
        for (int i= 0; i < n + 2; ++i) {
            for (int j = 0; j < n + 2; ++j) {
                f[i][j] = -1;
            }
        }
        int[] bak = new int[n + 2];
        bak[0] = bak[n + 1] = 1;
        for (int i = 1; i < n + 1; ++i) {
            bak[i] = nums[i - 1];
        }
        return dp(bak, f, 0, n + 1);
    }
    
    private int dp(int[] nums, int[][] f, int x, int y) {
        if (f[x][y] != -1) {
            return f[x][y];
        }
        
        f[x][y] = 0;
        
        //枚举最后一个戳破的气球的位置
        for (int i = x + 1; i < y; ++i) {
            f[x][y] = Math.max(f[x][y], nums[i] * nums[x] * nums[y] + dp(nums,f,  x, i) + dp(nums, f, i, y));
        }
        return f[x][y];
        
    }
    
    
}
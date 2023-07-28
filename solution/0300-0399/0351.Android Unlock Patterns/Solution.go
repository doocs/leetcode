func numberOfPatterns(m int, n int) int {
	cross := [10][10]int{}
	vis := [10]bool{}
	cross[1][3] = 2
	cross[1][7] = 4
	cross[1][9] = 5
	cross[2][8] = 5
	cross[3][7] = 5
	cross[3][9] = 6
	cross[4][6] = 5
	cross[7][9] = 8
	cross[3][1] = 2
	cross[7][1] = 4
	cross[9][1] = 5
	cross[8][2] = 5
	cross[7][3] = 5
	cross[9][3] = 6
	cross[6][4] = 5
	cross[9][7] = 8
	var dfs func(int, int) int
	dfs = func(i, cnt int) int {
		if cnt > n {
			return 0
		}
		vis[i] = true
		ans := 0
		if cnt >= m {
			ans++
		}
		for j := 1; j < 10; j++ {
			x := cross[i][j]
			if !vis[j] && (x == 0 || vis[x]) {
				ans += dfs(j, cnt+1)
			}
		}
		vis[i] = false
		return ans
	}
	return dfs(1, 1)*4 + dfs(2, 1)*4 + dfs(5, 1)
}
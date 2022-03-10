func findCircleNum(isConnected [][]int) int {
	n := len(isConnected)
	vis := make([]bool, n)
	var dfs func(i int)
	dfs = func(i int) {
		vis[i] = true
		for j := 0; j < n; j++ {
			if !vis[j] && isConnected[i][j] == 1 {
				dfs(j)
			}
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		if !vis[i] {
			dfs(i)
			ans++
		}
	}
	return ans
}
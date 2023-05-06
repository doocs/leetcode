func findCircleNum(isConnected [][]int) (ans int) {
	n := len(isConnected)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		for j, x := range isConnected[i] {
			if !vis[j] && x == 1 {
				dfs(j)
			}
		}
	}
	for i, v := range vis {
		if !v {
			ans++
			dfs(i)
		}
	}
	return
}
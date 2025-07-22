func permute(n int) (ans [][]int) {
	vis := make([]bool, n+1)
	t := make([]int, n)
	var dfs func(i int)
	dfs = func(i int) {
		if i >= n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j := 1; j <= n; j++ {
			if !vis[j] && (i == 0 || t[i-1]%2 != j%2) {
				vis[j] = true
				t[i] = j
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}

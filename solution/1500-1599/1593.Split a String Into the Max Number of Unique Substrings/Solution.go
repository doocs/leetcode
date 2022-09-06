func maxUniqueSplit(s string) int {
	ans := 1
	vis := map[string]bool{}

	var dfs func(i, t int)
	dfs = func(i, t int) {
		if i >= len(s) {
			ans = max(ans, t)
			return
		}
		for j := i + 1; j <= len(s); j++ {
			x := s[i:j]
			if !vis[x] {
				vis[x] = true
				dfs(j, t+1)
				vis[x] = false
			}
		}
	}
	dfs(0, 0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func buildWall(height int, width int, bricks []int) int {
	mod := int(1e9) + 7
	res := [][]int{}
	t := []int{}
	var dfs func(v int)
	dfs = func(v int) {
		if v > width {
			return
		}
		if v == width {
			cp := make([]int, len(t))
			copy(cp, t)
			res = append(res, cp)
			return
		}
		for _, x := range bricks {
			t = append(t, x)
			dfs(v + x)
			t = t[:len(t)-1]
		}
	}
	check := func(a, b []int) bool {
		s1, s2 := a[0], b[0]
		i, j := 1, 1
		for i < len(a) && j < len(b) {
			if s1 == s2 {
				return false
			}
			if s1 < s2 {
				s1 += a[i]
				i++
			} else {
				s2 += b[j]
				j++
			}
		}
		return true
	}
	dfs(0)
	n := len(res)
	g := make([][]int, n)
	for i := 0; i < n; i++ {
		if check(res[i], res[i]) {
			g[i] = append(g[i], i)
		}
		for j := i + 1; j < n; j++ {
			if check(res[i], res[j]) {
				g[i] = append(g[i], j)
				g[j] = append(g[j], i)
			}
		}
	}
	dp := make([][]int, height)
	for i := range dp {
		dp[i] = make([]int, n)
	}
	for j := 0; j < n; j++ {
		dp[0][j] = 1
	}
	for i := 1; i < height; i++ {
		for j := 0; j < n; j++ {
			for _, k := range g[j] {
				dp[i][j] += dp[i-1][k]
				dp[i][j] %= mod
			}
		}
	}
	ans := 0
	for j := 0; j < n; j++ {
		ans += dp[height-1][j]
		ans %= mod
	}
	return ans
}
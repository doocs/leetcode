func stoneGameV(stoneValue []int) int {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		ans, a := 0, 0
		for k := i; k < j; k++ {
			a += stoneValue[k]
			b := s[j+1] - s[i] - a
			if a < b {
				if ans >= a*2 {
					continue
				}
				ans = max(ans, a+dfs(i, k))
			} else if a > b {
				if ans >= b*2 {
					break
				}
				ans = max(ans, b+dfs(k+1, j))
			} else {
				ans = max(ans, max(a+dfs(i, k), b+dfs(k+1, j)))
			}
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, n-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
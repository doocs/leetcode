func minimumWhiteTiles(floor string, numCarpets int, carpetLen int) int {
	n := len(floor)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, numCarpets+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	s := make([]int, n+1)
	for i, c := range floor {
		s[i+1] = s[i] + int(c-'0')
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= n {
			return 0
		}
		if j == 0 {
			return s[n] - s[i]
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		if s[i+1] == s[i] {
			return dfs(i+1, j)
		}
		ans := min(1+dfs(i+1, j), dfs(i+carpetLen, j-1))
		f[i][j] = ans
		return ans
	}
	return dfs(0, numCarpets)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
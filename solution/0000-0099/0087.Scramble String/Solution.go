func isScramble(s1 string, s2 string) bool {
	n := len(s1)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n+1)
		}
	}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if k == 1 {
			return s1[i] == s2[j]
		}
		if f[i][j][k] != 0 {
			return f[i][j][k] == 1
		}
		f[i][j][k] = 2
		for h := 1; h < k; h++ {
			if (dfs(i, j, h) && dfs(i+h, j+h, k-h)) || (dfs(i+h, j, k-h) && dfs(i, j+k-h, h)) {
				f[i][j][k] = 1
				return true
			}
		}
		return false
	}
	return dfs(0, 0, n)
}
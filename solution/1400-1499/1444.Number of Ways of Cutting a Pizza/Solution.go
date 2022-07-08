func ways(pizza []string, k int) int {
	mod := int(1e9) + 7
	m, n := len(pizza), len(pizza[0])
	f := make([][][]int, m)
	s := make([][]int, m+1)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k)
			for h := range f[i][j] {
				f[i][j][h] = -1
			}
		}
	}
	for i := range s {
		s[i] = make([]int, n+1)
	}
	for i, p := range pizza {
		for j, v := range p {
			s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j]
			if v == 'A' {
				s[i+1][j+1]++
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		if k == 0 {
			if s[m][n]-s[m][j]-s[i][n]+s[i][j] > 0 {
				return 1
			}
			return 0
		}
		res := 0
		for x := i + 1; x < m; x++ {
			if s[x][n]-s[x][j]-s[i][n]+s[i][j] > 0 {
				res = (res + dfs(x, j, k-1)) % mod
			}
		}
		for y := j + 1; y < n; y++ {
			if s[m][y]-s[m][j]-s[i][y]+s[i][j] > 0 {
				res = (res + dfs(i, y, k-1)) % mod
			}
		}
		f[i][j][k] = res
		return res
	}
	return dfs(0, 0, k-1)
}
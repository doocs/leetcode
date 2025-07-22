func countWinningSequences(s string) int {
	const mod int = 1e9 + 7
	d := [26]int{}
	d['W'-'A'] = 1
	d['E'-'A'] = 2
	n := len(s)
	f := make([][][4]int, n)
	for i := range f {
		f[i] = make([][4]int, n+n+1)
		for j := range f[i] {
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	calc := func(x, y int) int {
		if x == y {
			return 0
		}
		if x < y {
			if x == 0 && y == 2 {
				return 1
			}
			return -1
		}
		if x == 2 && y == 0 {
			return -1
		}
		return 1
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if n-i <= j-n {
			return 0
		}
		if i >= n {
			if j-n < 0 {
				return 1
			}
			return 0
		}
		if v := f[i][j][k]; v != -1 {
			return v
		}
		ans := 0
		for l := 0; l < 3; l++ {
			if l == k {
				continue
			}
			ans = (ans + dfs(i+1, j+calc(d[s[i]-'A'], l), l)) % mod
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, n, 3)
}

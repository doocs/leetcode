func numDupDigitsAtMostN(n int) int {
	s := []byte(strconv.Itoa(n))
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, 1<<10)
		for j := range f[i] {
			f[i][j] = -1
		}
	}

	var dfs func(i, mask int, lead, limit bool) int
	dfs = func(i, mask int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && f[i][mask] != -1 {
			return f[i][mask]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if lead && j == 0 {
				ans += dfs(i+1, mask, true, limit && j == up)
			} else if mask>>j&1 == 0 {
				ans += dfs(i+1, mask|(1<<j), false, limit && j == up)
			}
		}
		if !lead && !limit {
			f[i][mask] = ans
		}
		return ans
	}
	return n - dfs(0, 0, true, true)
}

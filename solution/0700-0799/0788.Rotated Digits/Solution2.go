func rotatedDigits(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][2]int, m)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, ok int, limit bool) int
	dfs = func(i, ok int, limit bool) int {
		if i >= m {
			return ok
		}
		if !limit && f[i][ok] != -1 {
			return f[i][ok]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 0 || j == 1 || j == 8 {
				ans += dfs(i+1, ok, limit && j == up)
			} else if j == 2 || j == 5 || j == 6 || j == 9 {
				ans += dfs(i+1, 1, limit && j == up)
			}
		}
		if !limit {
			f[i][ok] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}

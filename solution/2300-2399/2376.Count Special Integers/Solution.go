func countSpecialNumbers(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][1 << 10]int, m+1)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int, bool, bool) int
	dfs = func(i, mask int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !limit && !lead && f[i][mask] != -1 {
			return f[i][mask]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if mask>>j&1 == 1 {
				continue
			}
			if lead && j == 0 {
				ans += dfs(i+1, mask, true, limit && j == up)
			} else {
				ans += dfs(i+1, mask|1<<j, false, limit && j == up)
			}
		}
		if !limit && !lead {
			f[i][mask] = ans
		}
		return ans
	}
	return dfs(0, 0, true, true)
}

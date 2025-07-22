func countNumbersWithUniqueDigits(n int) int {
	f := make([][1 << 10]int, n)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, mask int, lead bool) int
	dfs = func(i, mask int, lead bool) int {
		if i < 0 {
			return 1
		}
		if !lead && f[i][mask] != -1 {
			return f[i][mask]
		}
		ans := 0
		for j := 0; j < 10; j++ {
			if mask>>j&1 == 1 {
				continue
			}
			if lead && j == 0 {
				ans += dfs(i-1, mask, true)
			} else {
				ans += dfs(i-1, mask|1<<j, false)
			}
		}
		if !lead {
			f[i][mask] = ans
		}
		return ans
	}
	return dfs(n-1, 0, true)
}

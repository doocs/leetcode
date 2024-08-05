func findIntegers(n int) int {
	s := strconv.FormatInt(int64(n), 2)
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = []int{-1, -1}
	}
	var dfs func(int, int, bool) int
	dfs = func(pos int, pre int, limit bool) int {
		if pos >= m {
			return 1
		}
		if !limit && f[pos][pre] != -1 {
			return f[pos][pre]
		}
		up := 1
		if limit {
			up = int(s[pos] - '0')
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if !(pre == 1 && i == 1) {
				ans += dfs(pos+1, i, limit && i == up)
			}
		}
		if !limit {
			f[pos][pre] = ans
		}
		return ans
	}
	return dfs(0, 0, true)
}

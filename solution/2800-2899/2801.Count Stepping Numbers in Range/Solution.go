func countSteppingNumbers(low string, high string) int {
	const mod = 1e9 + 7
	f := [110][10]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	num := high
	var dfs func(int, int, bool, bool) int
	dfs = func(pos, pre int, lead bool, limit bool) int {
		if pos >= len(num) {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && f[pos][pre] != -1 {
			return f[pos][pre]
		}
		var ans int
		up := 9
		if limit {
			up = int(num[pos] - '0')
		}
		for i := 0; i <= up; i++ {
			if i == 0 && lead {
				ans += dfs(pos+1, pre, true, limit && i == up)
			} else if pre == -1 || abs(pre-i) == 1 {
				ans += dfs(pos+1, i, false, limit && i == up)
			}
			ans %= mod
		}
		if !lead && !limit {
			f[pos][pre] = ans
		}
		return ans
	}
	a := dfs(0, -1, true, true)
	t := []byte(low)
	for i := len(t) - 1; i >= 0; i-- {
		if t[i] != '0' {
			t[i]--
			break
		}
		t[i] = '9'
	}
	num = string(t)
	f = [110][10]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	b := dfs(0, -1, true, true)
	return (a - b + mod) % mod
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
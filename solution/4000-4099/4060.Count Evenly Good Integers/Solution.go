func countEvenlyGoodIntegers(l int64, r int64) int64 {
	var s string
	var f [20][2][2]int64

	calc := func(x int64) int64 {
		s = strconv.FormatInt(x, 10)
		for i := range f {
			for j := range f[i] {
				for k := range f[i][j] {
					f[i][j][k] = -1
				}
			}
		}

		var dfs func(pos, st int, lim bool) int64
		dfs = func(pos, st int, lim bool) int64 {
			if pos >= len(s) {
				return int64(st ^ 1)
			}
			k := 0
			if lim {
				k = 1
			}
			if f[pos][st][k] != -1 {
				return f[pos][st][k]
			}
			up := 9
			if lim {
				up = int(s[pos] - '0')
			}
			var res int64
			for i := 0; i <= up; i++ {
				res += dfs(pos+1, (st+(i&1^1))%2, lim && i == up)
			}
			f[pos][st][k] = res
			return res
		}

		return dfs(0, 0, true)
	}

	return calc(r) - calc(l-1)
}

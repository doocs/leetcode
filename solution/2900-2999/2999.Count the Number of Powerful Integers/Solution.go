func numberOfPowerfulInt(start, finish int64, limit int, s string) int64 {
	t := strconv.FormatInt(start-1, 10)
	f := make([]int64, 20)
	for i := range f {
		f[i] = -1
	}

	var dfs func(int, bool) int64
	dfs = func(pos int, lim bool) int64 {
		if len(t) < len(s) {
			return 0
		}
		if !lim && f[pos] != -1 {
			return f[pos]
		}
		if len(t)-pos == len(s) {
			if lim {
				if s <= t[pos:] {
					return 1
				}
				return 0
			}
			return 1
		}

		ans := int64(0)
		up := 9
		if lim {
			up = int(t[pos] - '0')
		}
		up = min(up, limit)
		for i := 0; i <= up; i++ {
			ans += dfs(pos+1, lim && i == int(t[pos]-'0'))
		}
		if !lim {
			f[pos] = ans
		}
		return ans
	}

	a := dfs(0, true)
	t = strconv.FormatInt(finish, 10)
	for i := range f {
		f[i] = -1
	}
	b := dfs(0, true)
	return b - a
}
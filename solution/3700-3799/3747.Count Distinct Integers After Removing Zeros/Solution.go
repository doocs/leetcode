func countDistinct(n int64) int64 {
	s := []byte(fmt.Sprint(n))
	m := len(s)
	var f [20][2][2][2]int64
	for i := range f {
		for j := range f[i] {
			for k := range f[i][j] {
				for t := range f[i][j][k] {
					f[i][j][k][t] = -1
				}
			}
		}
	}

	var dfs func(i, zero, lead, limit int) int64
	dfs = func(i, zero, lead, limit int) int64 {
		if i == m {
			if zero == 0 && lead == 0 {
				return 1
			}
			return 0
		}

		if limit == 0 && f[i][zero][lead][limit] != -1 {
			return f[i][zero][lead][limit]
		}

		up := 9
		if limit == 1 {
			up = int(s[i] - '0')
		}

		var ans int64 = 0
		for d := 0; d <= up; d++ {
			nxtZero := zero
			if d == 0 && lead == 0 {
				nxtZero = 1
			}
			nxtLead := 0
			if lead == 1 && d == 0 {
				nxtLead = 1
			}
			nxtLimit := 0
			if limit == 1 && d == up {
				nxtLimit = 1
			}
			ans += dfs(i+1, nxtZero, nxtLead, nxtLimit)
		}

		if limit == 0 {
			f[i][zero][lead][limit] = ans
		}
		return ans
	}

	return dfs(0, 0, 1, 1)
}

func countFancy(l int64, r int64) int64 {
	check := func(s int) bool {
		if s < 100 {
			return s%11 != 0
		}
		mid := (s / 10) % 10
		last := s % 10
		return mid > 1 && mid < last
	}

	var num string
	var n int
	var f [][][][]int64

	var dfs func(pos, s, prev, st int, lim bool) int64
	dfs = func(pos, s, prev, st int, lim bool) int64 {
		if pos >= n {
			if st != 3 {
				return 1
			}
			if check(s) {
				return 1
			}
			return 0
		}

		if !lim && f[pos][s][prev][st] != -1 {
			return f[pos][s][prev][st]
		}

		up := 9
		if lim {
			up = int(num[pos] - '0')
		}

		var res int64 = 0

		for i := 0; i <= up; i++ {
			nxtSt := st

			if st == 0 {
				if prev == 0 {
					nxtSt = 0
				} else if i > prev {
					nxtSt = 1
				} else if i < prev {
					nxtSt = 2
				} else {
					nxtSt = 3
				}
			} else if st == 1 {
				if i > prev {
					nxtSt = 1
				} else {
					nxtSt = 3
				}
			} else if st == 2 {
				if i < prev {
					nxtSt = 2
				} else {
					nxtSt = 3
				}
			} else {
				nxtSt = 3
			}

			res += dfs(pos+1, s+i, i, nxtSt, lim && i == up)
		}

		if !lim {
			f[pos][s][prev][st] = res
		}

		return res
	}

	calc := func(x int64) int64 {
		num = strconv.FormatInt(x, 10)
		n = len(num)

		f = make([][][][]int64, n)
		for i := 0; i < n; i++ {
			f[i] = make([][][]int64, 9*n+1)
			for j := 0; j <= 9*n; j++ {
				f[i][j] = make([][]int64, 10)
				for k := 0; k < 10; k++ {
					f[i][j][k] = make([]int64, 4)
					for t := 0; t < 4; t++ {
						f[i][j][k][t] = -1
					}
				}
			}
		}

		return dfs(0, 0, 0, 0, true)
	}

	return calc(r) - calc(l-1)
}

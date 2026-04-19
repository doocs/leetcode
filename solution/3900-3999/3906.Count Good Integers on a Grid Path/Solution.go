func countGoodIntegersOnPath(l int64, r int64, directions string) int64 {
	key := make([]bool, 16)
	row, col := 0, 0
	key[0] = true
	for _, c := range directions {
		if c == 'D' {
			row++
		} else {
			col++
		}
		key[row*4+col] = true
	}

	var s string
	var f [16][10]int64

	var dfs func(int, int, bool) int64
	dfs = func(pos int, last int, lim bool) int64 {
		if pos == 16 {
			return 1
		}
		if !lim && f[pos][last] != -1 {
			return f[pos][last]
		}

		var res int64 = 0
		start := 0
		if key[pos] {
			start = last
		}
		end := 9
		if lim {
			end = int(s[pos] - '0')
		}

		for i := start; i <= end; i++ {
			nextLast := last
			if key[pos] {
				nextLast = i
			}
			res += dfs(pos+1, nextLast, lim && (i == end))
		}

		if !lim {
			f[pos][last] = res
		}
		return res
	}

	calc := func(x int64) int64 {
		if x < 0 {
			return 0
		}
		t := strconv.FormatInt(x, 10)
		s = fmt.Sprintf("%016s", t)
		for i := 0; i < 16; i++ {
			for j := 0; j < 10; j++ {
				f[i][j] = -1
			}
		}
		return dfs(0, 0, true)
	}

	return calc(r) - calc(l-1)
}

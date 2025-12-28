func countBalanced(low int64, high int64) int64 {
	if high < 11 {
		return 0
	}
	if low < 11 {
		low = 11
	}
	const base = 90

	var num []byte
	var f [20][181]int64

	var dfs func(pos int, diff int, lim bool) int64
	dfs = func(pos int, diff int, lim bool) int64 {
		if pos >= len(num) {
			if diff == 0 {
				return 1
			}
			return 0
		}
		if !lim && f[pos][diff+base] != -1 {
			return f[pos][diff+base]
		}
		up := 9
		if lim {
			up = int(num[pos] - '0')
		}
		var res int64 = 0
		for i := 0; i <= up; i++ {
			if pos%2 == 0 {
				res += dfs(pos+1, diff+i, lim && i == up)
			} else {
				res += dfs(pos+1, diff-i, lim && i == up)
			}
		}
		if !lim {
			f[pos][diff+base] = res
		}
		return res
	}

	num = []byte(fmt.Sprint(low - 1))
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	a := dfs(0, 0, true)

	num = []byte(fmt.Sprint(high))
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	b := dfs(0, 0, true)

	return b - a
}

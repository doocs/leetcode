import "strconv"

func totalWaviness(num1 int64, num2 int64) int64 {
	return calc(num2) - calc(num1-1)
}

func calc(x int64) int64 {
	if x < 0 {
		return 0
	}
	s := strconv.FormatInt(x, 10)
	n := len(s)
	var fCnt, fWav [20][11][11][2]int64
	var vis [20][11][11][2]bool
	var dfs func(pos, prev2, prev1, started int, limit bool) (int64, int64)
	dfs = func(pos, prev2, prev1, started int, limit bool) (int64, int64) {
		if pos == n {
			return int64(started), 0
		}
		if !limit && vis[pos][prev2][prev1][started] {
			return fCnt[pos][prev2][prev1][started], fWav[pos][prev2][prev1][started]
		}
		up := 9
		if limit {
			up = int(s[pos] - '0')
		}
		var c, w int64
		for d := 0; d <= up; d++ {
			nlimit := limit && d == up
			ns, np2, np1, add := started, prev1, d, 0
			if started == 0 {
				if d == 0 {
					ns, np2, np1 = 0, 10, 10
				} else {
					ns, np2, np1 = 1, 10, d
				}
			} else if prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d)) {
				add = 1
			}
			tc, tw := dfs(pos+1, np2, np1, ns, nlimit)
			c += tc
			w += tw + tc*int64(add)
		}
		if !limit {
			vis[pos][prev2][prev1][started] = true
			fCnt[pos][prev2][prev1][started] = c
			fWav[pos][prev2][prev1][started] = w
		}
		return c, w
	}
	_, wav := dfs(0, 10, 10, 0, true)
	return wav
}

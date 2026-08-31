func maxActiveSectionsAfterTrade(s string, queries [][]int) []int {
	n := len(s)
	active := 0
	for i := 0; i < n; i++ {
		if s[i] == '1' {
			active++
		}
	}
	if strings.IndexByte(s, '0') < 0 {
		ans := make([]int, len(queries))
		for i := range ans {
			ans[i] = active
		}
		return ans
	}

	zeros := make([][2]int, 0, n)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		if s[i] == '0' {
			if i > 0 && s[i-1] == '0' {
				zeros[len(zeros)-1][1]++
			} else {
				zeros = append(zeros, [2]int{i, 1})
			}
		}
		idx[i] = len(zeros) - 1
	}

	m := len(zeros) - 1
	K := 0
	if m > 0 {
		K = bits.Len(uint(m))
	}
	st := make([][]int, max(K, 1))
	for k := range st {
		st[k] = make([]int, max(m, 0))
	}
	for i := 0; i < m; i++ {
		st[0][i] = zeros[i][1] + zeros[i+1][1]
	}
	for k := 1; k < K; k++ {
		for i := 0; i+(1<<k) <= m; i++ {
			st[k][i] = max(st[k-1][i], st[k-1][i+(1<<(k-1))])
		}
	}

	query := func(l, r int) int {
		if l > r || m <= 0 {
			return 0
		}
		k := bits.Len(uint(r-l+1)) - 1
		return max(st[k][l], st[k][r-(1<<k)+1])
	}

	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		L, R := q[0], q[1]
		iL, iR := idx[L], idx[R]
		cntL, cntR := -1, -1
		if iL >= 0 {
			cntL = zeros[iL][1] - (L - zeros[iL][0])
		}
		if iR >= 0 {
			cntR = R - zeros[iR][0] + 1
		}
		start := iL + 1
		end := iR
		if s[R] == '0' {
			end--
		}
		best := active
		if start < end {
			best = max(best, active+query(start, end-1))
		}
		if s[L] == '0' && s[R] == '0' && iL+1 == iR {
			best = max(best, active+cntL+cntR)
		}
		add := 0
		if s[R] == '1' {
			add = 1
		}
		if s[L] == '0' && iL+1 < iR+add {
			best = max(best, active+cntL+zeros[iL+1][1])
		}
		if s[R] == '0' && iL < iR-1 {
			best = max(best, active+cntR+zeros[iR-1][1])
		}
		ans = append(ans, best)
	}
	return ans
}

func maxStudents(seats [][]byte) int {
	m, n := len(seats), len(seats[0])
	ss := make([]int, m)
	f := make([][]int, 1<<n)
	for i, seat := range seats {
		for j, c := range seat {
			if c == '.' {
				ss[i] |= 1 << j
			}
		}
	}
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(seat, i int) int {
		if f[seat][i] != -1 {
			return f[seat][i]
		}
		ans := 0
		for mask := 0; mask < 1<<n; mask++ {
			if (seat|mask) != seat || (mask&(mask<<1)) != 0 {
				continue
			}
			cnt := bits.OnesCount(uint(mask))
			if i == m-1 {
				ans = max(ans, cnt)
			} else {
				nxt := ss[i+1] & ^(mask >> 1) & ^(mask << 1)
				ans = max(ans, cnt+dfs(nxt, i+1))
			}
		}
		f[seat][i] = ans
		return ans
	}
	return dfs(ss[0], 0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
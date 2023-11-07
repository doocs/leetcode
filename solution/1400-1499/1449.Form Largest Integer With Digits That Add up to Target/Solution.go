func largestNumber(cost []int, target int) string {
	const inf = 1 << 30
	f := make([][]int, 10)
	g := make([][]int, 10)
	for i := range f {
		f[i] = make([]int, target+1)
		g[i] = make([]int, target+1)
		for j := range f[i] {
			f[i][j] = -inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= 9; i++ {
		c := cost[i-1]
		for j := 0; j <= target; j++ {
			if j < c || f[i][j-c]+1 < f[i-1][j] {
				f[i][j] = f[i-1][j]
				g[i][j] = j
			} else {
				f[i][j] = f[i][j-c] + 1
				g[i][j] = j - c
			}
		}
	}
	if f[9][target] < 0 {
		return "0"
	}
	ans := []byte{}
	for i, j := 9, target; i > 0; {
		if g[i][j] == j {
			i--
		} else {
			ans = append(ans, '0'+byte(i))
			j = g[i][j]
		}
	}
	return string(ans)
}
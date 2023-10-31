func solve(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	m := int(math.Sqrt(float64(n)))
	const mod int = 1e9 + 7
	suf := make([][]int, m+1)
	for i := range suf {
		suf[i] = make([]int, n+1)
		for j := n - 1; j >= 0; j-- {
			suf[i][j] = (suf[i][min(n, j+i)] + nums[j]) % mod
		}
	}
	for _, q := range queries {
		x, y := q[0], q[1]
		if y <= m {
			ans = append(ans, suf[y][x])
		} else {
			s := 0
			for i := x; i < n; i += y {
				s = (s + nums[i]) % mod
			}
			ans = append(ans, s)
		}
	}
	return
}
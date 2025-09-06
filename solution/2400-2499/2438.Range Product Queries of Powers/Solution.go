func productQueries(n int, queries [][]int) []int {
	var powers []int
	for n > 0 {
		x := n & -n
		powers = append(powers, x)
		n -= x
	}
	const mod = 1_000_000_007
	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		l, r := q[0], q[1]
		x := 1
		for j := l; j <= r; j++ {
			x = x * powers[j] % mod
		}
		ans = append(ans, x)
	}
	return ans
}

func productQueries(n int, queries [][]int) []int {
	var mod int = 1e9 + 7
	powers := []int{}
	for n > 0 {
		x := n & -n
		powers = append(powers, x)
		n -= x
	}
	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		x := 1
		for _, y := range powers[l : r+1] {
			x = (x * y) % mod
		}
		ans[i] = x
	}
	return ans
}
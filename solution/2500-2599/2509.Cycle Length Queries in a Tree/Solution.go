func cycleLengthQueries(n int, queries [][]int) []int {
	ans := []int{}
	for _, q := range queries {
		a, b := q[0], q[1]
		t := 1
		for a != b {
			if a > b {
				a >>= 1
			} else {
				b >>= 1
			}
			t++
		}
		ans = append(ans, t)
	}
	return ans
}
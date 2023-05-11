func xorQueries(arr []int, queries [][]int) (ans []int) {
	n := len(arr)
	s := make([]int, n+1)
	for i, x := range arr {
		s[i+1] = s[i] ^ x
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, s[r+1]^s[l])
	}
	return
}
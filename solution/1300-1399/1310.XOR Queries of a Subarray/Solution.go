func xorQueries(arr []int, queries [][]int) []int {
	xors := make([]int, len(arr)+1)
	for i, v := range arr {
		xors[i+1] = xors[i] ^ v
	}
	var ans []int
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, xors[l]^xors[r+1])
	}
	return ans
}
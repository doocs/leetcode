func circularPermutation(n int, start int) []int {
	g := make([]int, 1<<n)
	j := 0
	for i := range g {
		g[i] = i ^ (i >> 1)
		if g[i] == start {
			j = i
		}
	}
	return append(g[j:], g[:j]...)
}
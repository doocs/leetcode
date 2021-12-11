func findJudge(n int, trust [][]int) int {
	N := 1001
	c1 := make([]int, N)
	c2 := make([]int, N)
	for _, e := range trust {
		c1[e[0]]++
		c2[e[1]]++
	}
	for i := 1; i < N; i++ {
		if c1[i] == 0 && c2[i] == n-1 {
			return i
		}
	}
	return -1
}
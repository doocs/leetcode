func hanota(A []int, B []int, C []int) []int {
	var dfs func(n int, a, b, c *[]int)
	dfs = func(n int, a, b, c *[]int) {
		if n == 1 {
			*c = append(*c, (*a)[len(*a)-1])
			*a = (*a)[:len(*a)-1]
			return
		}
		dfs(n-1, a, c, b)
		*c = append(*c, (*a)[len(*a)-1])
		*a = (*a)[:len(*a)-1]
		dfs(n-1, b, a, c)
	}
	dfs(len(A), &A, &B, &C)
	return C
}
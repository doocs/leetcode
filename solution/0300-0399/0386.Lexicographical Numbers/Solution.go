func lexicalOrder(n int) []int {
	var res []int
	var dfs func(int, int)
	dfs = func(i, n int) {
		if i > n {
			return
		}
		res = append(res, i)
		for j := 0; j < 10; j++ {
			dfs(i*10+j, n)
		}
	}

	for i := 1; i < 10; i++ {
		dfs(i, n)
	}
	return res
}
func generateValidStrings(n int, k int) []string {
	ans := []string{}
	path := make([]byte, 0, n)

	var dfs func(int, int)
	dfs = func(i, tot int) {
		if i >= n {
			ans = append(ans, string(path))
			return
		}

		path = append(path, '0')
		dfs(i+1, tot)
		path = path[:len(path)-1]

		if (len(path) == 0 || path[len(path)-1] == '0') && tot+i <= k {
			path = append(path, '1')
			dfs(i+1, tot+i)
			path = path[:len(path)-1]
		}
	}

	dfs(0, 0)

	return ans
}
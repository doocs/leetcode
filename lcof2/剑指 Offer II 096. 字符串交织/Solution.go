func isInterleave(s1 string, s2 string, s3 string) bool {
	m, n := len(s1), len(s2)
	if m+n != len(s3) {
		return false
	}

	memo := make(map[int]bool)

	var dfs func(int, int) bool
	dfs = func(i, j int) bool {
		if i == m && j == n {
			return true
		}
		if v, ok := memo[i*100+j]; ok {
			return v
		}

		ret := (i < m && s1[i] == s3[i+j] && dfs(i+1, j)) ||
			(j < n && s2[j] == s3[i+j] && dfs(i, j+1))

		memo[i*100+j] = ret
		return ret
	}

	return dfs(0, 0)
}

func minOperations(s1 string, s2 string, x int) int {
	idx := []int{}
	for i := range s1 {
		if s1[i] != s2[i] {
			idx = append(idx, i)
		}
	}
	m := len(idx)
	if m&1 == 1 {
		return -1
	}
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = dfs(i+1, j-1) + x
		f[i][j] = min(f[i][j], dfs(i+2, j)+idx[i+1]-idx[i])
		f[i][j] = min(f[i][j], dfs(i, j-2)+idx[j]-idx[j-1])
		return f[i][j]
	}
	return dfs(0, m-1)
}
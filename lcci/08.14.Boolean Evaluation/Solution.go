func countEval(s string, result int) int {
	memo := map[string][]int{}
	var dfs func(string) []int
	dfs = func(s string) []int {
		if v, ok := memo[s]; ok {
			return v
		}
		res := make([]int, 2)
		if len(s) == 1 {
			res[s[0]-'0'] = 1
			return res
		}
		for k, c := range s {
			if c == '0' || c == '1' {
				continue
			}
			left, right := dfs(s[:k]), dfs(s[k+1:])
			for i, v1 := range left {
				for j, v2 := range right {
					v := 0
					if c == '&' {
						v = i & j
					} else if c == '|' {
						v = i | j
					} else if c == '^' {
						v = i ^ j
					}
					res[v] += v1 * v2
				}
			}
		}
		memo[s] = res
		return res
	}
	ans := dfs(s)
	if result == 0 || result == 1 {
		return ans[result]
	}
	return 0
}
var memo = map[string][]int{}

func diffWaysToCompute(expression string) []int {
	return dfs(expression)
}

func dfs(exp string) []int {
	if v, ok := memo[exp]; ok {
		return v
	}
	if len(exp) < 3 {
		v, _ := strconv.Atoi(exp)
		return []int{v}
	}
	ans := []int{}
	for i, c := range exp {
		if c == '-' || c == '+' || c == '*' {
			left, right := dfs(exp[:i]), dfs(exp[i+1:])
			for _, a := range left {
				for _, b := range right {
					if c == '-' {
						ans = append(ans, a-b)
					} else if c == '+' {
						ans = append(ans, a+b)
					} else {
						ans = append(ans, a*b)
					}
				}
			}
		}
	}
	memo[exp] = ans
	return ans
}
func braceExpansionII(expression string) []string {
	s := map[string]struct{}{}
	var dfs func(exp string)
	dfs = func(exp string) {
		j := strings.IndexByte(exp, '}')
		if j == -1 {
			s[exp] = struct{}{}
			return
		}
		i := j
		for exp[i] != '{' {
			i--
		}
		a, c := exp[:i], exp[j+1:]
		for _, b := range strings.Split(exp[i+1:j], ",") {
			dfs(a + b + c)
		}
	}
	dfs(expression)
	ans := []string{}
	for v := range s {
		ans = append(ans, v)
	}
	sort.Strings(ans)
	return ans
}
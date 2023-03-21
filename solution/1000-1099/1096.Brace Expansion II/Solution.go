func braceExpansionII(expression string) []string {
	s := map[string]struct{}{}
	var dfs func(string)
	dfs = func(exp string) {
		j := strings.Index(exp, "}")
		if j == -1 {
			s[exp] = struct{}{}
			return
		}
		i := strings.LastIndex(exp[:j], "{")
		a, c := exp[:i], exp[j+1:]
		for _, b := range strings.Split(exp[i+1:j], ",") {
			dfs(a + b + c)
		}
	}
	dfs(expression)
	ans := make([]string, 0, len(s))
	for k := range s {
		ans = append(ans, k)
	}
	sort.Strings(ans)
	return ans
}
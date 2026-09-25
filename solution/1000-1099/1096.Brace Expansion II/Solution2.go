func braceExpansionII(expression string) []string {
	exp := expression
	i := 0
	var parseExpr func() map[string]struct{}
	var parseTerm func() map[string]struct{}
	parseExpr = func() map[string]struct{} {
		res := parseTerm()
		for i < len(exp) && exp[i] == ',' {
			i++
			for w := range parseTerm() {
				res[w] = struct{}{}
			}
		}
		return res
	}
	parseTerm = func() map[string]struct{} {
		res := map[string]struct{}{"": {}}
		for i < len(exp) && exp[i] != ',' && exp[i] != '}' {
			cur := map[string]struct{}{}
			if exp[i] == '{' {
				i++
				cur = parseExpr()
				i++
			} else {
				j := i + 1
				for j < len(exp) && exp[j] >= 'a' && exp[j] <= 'z' {
					j++
				}
				cur[exp[i:j]] = struct{}{}
				i = j
			}
			nxt := map[string]struct{}{}
			for a := range res {
				for b := range cur {
					nxt[a+b] = struct{}{}
				}
			}
			res = nxt
		}
		return res
	}
	all := parseExpr()
	ans := make([]string, 0, len(all))
	for w := range all {
		ans = append(ans, w)
	}
	sort.Strings(ans)
	return ans
}

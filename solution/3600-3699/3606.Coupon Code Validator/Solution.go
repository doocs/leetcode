func validateCoupons(code []string, businessLine []string, isActive []bool) []string {
	idx := []int{}
	bs := map[string]struct{}{
		"electronics": {},
		"grocery":     {},
		"pharmacy":    {},
		"restaurant":  {},
	}

	check := func(s string) bool {
		if len(s) == 0 {
			return false
		}
		for _, c := range s {
			if !unicode.IsLetter(c) && !unicode.IsDigit(c) && c != '_' {
				return false
			}
		}
		return true
	}

	for i := range code {
		if isActive[i] {
			if _, ok := bs[businessLine[i]]; ok && check(code[i]) {
				idx = append(idx, i)
			}
		}
	}

	sort.Slice(idx, func(i, j int) bool {
		if businessLine[idx[i]] != businessLine[idx[j]] {
			return businessLine[idx[i]] < businessLine[idx[j]]
		}
		return code[idx[i]] < code[idx[j]]
	})

	ans := make([]string, 0, len(idx))
	for _, i := range idx {
		ans = append(ans, code[i])
	}
	return ans
}

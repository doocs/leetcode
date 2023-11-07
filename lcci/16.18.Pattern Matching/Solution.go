func patternMatching(pattern string, value string) bool {
	cnt := [2]int{}
	for _, c := range pattern {
		cnt[c-'a']++
	}
	n := len(value)
	if cnt[0] == 0 {
		return n%cnt[1] == 0 && strings.Repeat(value[:n/cnt[1]], cnt[1]) == value
	}
	if cnt[1] == 0 {
		return n%cnt[0] == 0 && strings.Repeat(value[:n/cnt[0]], cnt[0]) == value
	}
	check := func(la, lb int) bool {
		i := 0
		a, b := "", ""
		for _, c := range pattern {
			if c == 'a' {
				if a != "" && value[i:i+la] != a {
					return false
				}
				a = value[i : i+la]
				i += la
			} else {
				if b != "" && value[i:i+lb] != b {
					return false
				}
				b = value[i : i+lb]
				i += lb
			}
		}
		return a != b
	}
	for la := 0; la <= n; la++ {
		if la*cnt[0] > n {
			break
		}
		if (n-la*cnt[0])%cnt[1] == 0 {
			lb := (n - la*cnt[0]) / cnt[1]
			if check(la, lb) {
				return true
			}
		}
	}
	return false
}
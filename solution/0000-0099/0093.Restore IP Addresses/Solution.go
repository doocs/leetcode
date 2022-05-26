func restoreIpAddresses(s string) []string {
	check := func(s string) bool {
		if i, _ := strconv.Atoi(s); i > 255 {
			return false
		}
		if s[0] == '0' && len(s) > 1 {
			return false
		}
		return true
	}
	var ans []string
	var dfs func(s string, t []string)
	dfs = func(s string, t []string) {
		if len(t) == 4 {
			if s == "" {
				ans = append(ans, strings.Join(t, "."))
			}
			return
		}
		for i := 1; i < 4 && i < len(s)+1; i++ {
			if check(s[0:i]) {
				t = append(t, s[0:i])
				dfs(s[i:], t)
				t = t[0 : len(t)-1]
			}
		}
	}
	var t []string
	dfs(s, t)
	return ans
}
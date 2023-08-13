func restoreIpAddresses(s string) (ans []string) {
	n := len(s)
	t := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n && len(t) == 4 {
			ans = append(ans, strings.Join(t, "."))
			return
		}
		if i >= n || len(t) == 4 {
			return
		}
		x := 0
		for j := i; j < i+3 && j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > 255 || (j > i && s[i] == '0') {
				break
			}
			t = append(t, s[i:j+1])
			dfs(j + 1)
			t = t[:len(t)-1]
		}
	}
	dfs(0)
	return
}
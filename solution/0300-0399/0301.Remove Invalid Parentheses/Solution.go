func removeInvalidParentheses(s string) []string {
	vis := map[string]bool{}
	l, r, n := 0, 0, len(s)
	for _, c := range s {
		if c == '(' {
			l++
		} else if c == ')' {
			if l > 0 {
				l--
			} else {
				r++
			}
		}
	}
	var dfs func(i, l, r, lcnt, rcnt int, t string)
	dfs = func(i, l, r, lcnt, rcnt int, t string) {
		if i == n {
			if l == 0 && r == 0 {
				vis[t] = true
			}
			return
		}
		if n-i < l+r || lcnt < rcnt {
			return
		}
		if s[i] == '(' && l > 0 {
			dfs(i+1, l-1, r, lcnt, rcnt, t)
		}
		if s[i] == ')' && r > 0 {
			dfs(i+1, l, r-1, lcnt, rcnt, t)
		}
		x, y := 0, 0
		if s[i] == '(' {
			x = 1
		} else if s[i] == ')' {
			y = 1
		}
		dfs(i+1, l, r, lcnt+x, rcnt+y, t+string(s[i]))
	}
	dfs(0, l, r, 0, 0, "")
	ans := make([]string, 0, len(vis))
	for v := range vis {
		ans = append(ans, v)
	}
	return ans
}
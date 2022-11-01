func isMatch(s string, p string) bool {
	var dp func(x, y int) bool
	dp = func(x, y int) bool {
		if y == len(p) {
			return x == len(s)
		}

		// 匹配
		if x < len(s) && (s[x] == p[y] || p[y] == '.') {
			// 下一个为 '*'
			if y+1 < len(p) && p[y+1] == '*' {
				return dp(x, y+2) || dp(x+1, y)
			}
			return dp(x+1, y+1)
		}
		// 不匹配但下一个元素为 '*'
		if y+1 < len(p) && p[y+1] == '*' {
			return dp(x, y+2)
		}
		return false
	}
	return dp(0, 0)
}

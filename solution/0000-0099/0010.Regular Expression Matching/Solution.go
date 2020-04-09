func isMatch(s string, p string) bool {
	//p only contains `.` or `*` or `[a-z]`
	lenP := len(p)
	lenS := len(s)
	if lenP == 0 {
		return lenS == 0
	}
	fm := lenS != 0 && (s[0] == p[0] || p[0] == '.')

	if len(p) >= 2 && p[1] == '*' {
		return isMatch(s, p[2:]) || fm && isMatch(s[1:], p)
	} else {
		return fm && isMatch(s[1:], p[1:])
	}
}
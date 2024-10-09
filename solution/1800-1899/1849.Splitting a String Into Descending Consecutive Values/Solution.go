func splitString(s string) bool {
	var dfs func(i, x int) bool
	dfs = func(i, x int) bool {
		if i >= len(s) {
			return true
		}
		y := 0
		r := len(s)
		if x < 0 {
			r--
		}
		for j := i; j < r; j++ {
			y = y*10 + int(s[j]-'0')
			if (x < 0 || x-y == 1) && dfs(j+1, y) {
				return true
			}
		}
		return false
	}
	return dfs(0, -1)
}

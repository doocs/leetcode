func splitString(s string) bool {
	var dfs func(i, x, k int) bool
	dfs = func(i, x, k int) bool {
		if i == len(s) {
			return k > 1
		}
		y := 0
		for j := i; j < len(s); j++ {
			y = y*10 + int(s[j]-'0')
			if y > int(1e10) {
				break
			}
			if (x == -1 || x-y == 1) && dfs(j+1, y, k+1) {
				return true
			}
		}
		return false
	}
	return dfs(0, -1, 0)
}
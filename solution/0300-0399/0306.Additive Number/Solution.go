func isAdditiveNumber(num string) bool {
	n := len(num)
	var dfs func(a, b int64, num string) bool
	dfs = func(a, b int64, num string) bool {
		if num == "" {
			return true
		}
		if a+b > 0 && num[0] == '0' {
			return false
		}
		for i := 1; i < min(len(num)+1, 19); i++ {
			c, _ := strconv.ParseInt(num[:i], 10, 64)
			if a+b == c {
				if dfs(b, c, num[i:]) {
					return true
				}
			}
		}
		return false
	}
	for i := 1; i < min(n-1, 19); i++ {
		for j := i + 1; j < min(n, i+19); j++ {
			if i > 1 && num[0] == '0' {
				break
			}
			if j-i > 1 && num[i] == '0' {
				continue
			}
			a, _ := strconv.ParseInt(num[:i], 10, 64)
			b, _ := strconv.ParseInt(num[i:j], 10, 64)
			if dfs(a, b, num[j:]) {
				return true
			}
		}
	}
	return false
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
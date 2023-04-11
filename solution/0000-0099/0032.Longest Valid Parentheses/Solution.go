func longestValidParentheses(s string) (ans int) {
	n := len(s)
	if n < 2 {
		return 0
	}
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		if s[i-1] == ')' {
			if s[i-2] == '(' {
				f[i] = f[i-2] + 2
			} else {
				j := i - f[i-1] - 1
				if j > 0 && s[j-1] == '(' {
					f[i] = f[i-1] + 2 + f[j-1]
				}
			}
			ans = max(ans, f[i])
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
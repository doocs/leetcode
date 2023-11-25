func longestValidParentheses(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		if s[i-1] == ')' {
			if s[i-2] == '(' {
				f[i] = f[i-2] + 2
			} else if j := i - f[i-1] - 1; j > 0 && s[j-1] == '(' {
				f[i] = f[i-1] + 2 + f[j-1]
			}
		}
	}
	return slices.Max(f)
}
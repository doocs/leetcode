func maxDepth(s string) (ans int) {
	d := 0
	for _, c := range s {
		if c == '(' {
			d++
			ans = max(ans, d)
		} else if c == ')' {
			d--
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
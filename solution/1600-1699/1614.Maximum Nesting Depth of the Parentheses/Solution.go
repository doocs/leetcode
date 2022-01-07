func maxDepth(s string) int {
	n, ans := 0, 0
	for _, c := range s {
		if c == '(' {
			n++
			if ans < n {
				ans = n
			}
		} else if c == ')' {
			n--
		}
	}
	return ans
}
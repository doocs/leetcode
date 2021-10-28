func maxDepth(s string) int {
	res, depth := 0, 0
	for _, c := range s {
		if c == '(' {
			depth++
			if depth > res {
				res = depth
			}
		} else if c == ')' {
			depth--
		}
	}
	return res
}
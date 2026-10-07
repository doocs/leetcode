func minRotations(n int, s string) int {
	total := 0
	for i := 1; i < n; i++ {
		diff := int(s[i]) - int(s[i-1])
		if diff < 0 {
			diff = -diff
		}
		total += min(diff, 10-diff)
	}

	first := int(s[0] - '0')
	last := int(s[n-1] - '0')
	toFirst := min(first, 10-first)
	ans := total + min(last, 10-last)

	for i := 1; i < n; i++ {
		pre := int(s[i-1])
		cur := int(s[i])
		diff := cur - pre
		if diff < 0 {
			diff = -diff
		}
		edge := min(diff, 10-diff)

		diff = pre - int(s[n-1])
		if diff < 0 {
			diff = -diff
		}
		toLast := min(diff, 10-diff)

		ans = min(ans, total-edge+toFirst+toLast)
	}

	return ans
}

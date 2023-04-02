func findTheLongestBalancedSubstring(s string) (ans int) {
	zero, one := 0, 0
	for _, c := range s {
		if c == '0' {
			if one > 0 {
				zero, one = 0, 0
			}
			zero++
		} else {
			one++
			ans = max(ans, 2*min(zero, one))
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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
func minimumDeletions(s string) int {
	ans, b := 0, 0
	for _, c := range s {
		if c == 'b' {
			b++
		} else {
			ans = min(ans+1, b)
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
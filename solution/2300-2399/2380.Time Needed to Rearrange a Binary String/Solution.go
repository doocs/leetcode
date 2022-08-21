func secondsToRemoveOccurrences(s string) int {
	ans, cnt := 0, 0
	for _, c := range s {
		if c == '0' {
			cnt++
		} else if cnt > 0 {
			ans = max(ans+1, cnt)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
func minOperations(s string) int {
	cnt := 0
	t := []rune("01")
	for i, c := range s {
		if t[i&1] != c {
			cnt++
		}
	}
	return min(cnt, len(s)-cnt)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
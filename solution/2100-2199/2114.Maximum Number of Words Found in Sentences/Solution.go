func mostWordsFound(sentences []string) int {
	count := func(s string, c rune) int {
		cnt := 0
		for _, ch := range s {
			if ch == c {
				cnt++
			}
		}
		return cnt
	}
	ans := 0
	for _, s := range sentences {
		ans = max(ans, 1+count(s, ' '))
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
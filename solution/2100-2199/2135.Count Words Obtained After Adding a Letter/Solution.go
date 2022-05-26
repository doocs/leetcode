func wordCount(startWords []string, targetWords []string) int {
	s := make(map[int]bool)
	for _, word := range startWords {
		mask := 0
		for _, c := range word {
			mask |= (1 << (c - 'a'))
		}
		s[mask] = true
	}
	ans := 0
	for _, word := range targetWords {
		mask := 0
		for _, c := range word {
			mask |= (1 << (c - 'a'))
		}
		for _, c := range word {
			t := mask ^ (1 << (c - 'a'))
			if s[t] {
				ans++
				break
			}
		}
	}
	return ans
}
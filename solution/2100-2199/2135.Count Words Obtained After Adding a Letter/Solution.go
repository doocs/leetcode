func wordCount(startWords []string, targetWords []string) (ans int) {
	s := map[int]bool{}
	for _, w := range startWords {
		x := 0
		for _, c := range w {
			x |= 1 << (c - 'a')
		}
		s[x] = true
	}
	for _, w := range targetWords {
		x := 0
		for _, c := range w {
			x |= 1 << (c - 'a')
		}
		for _, c := range w {
			if s[x^(1<<(c-'a'))] {
				ans++
				break
			}
		}
	}
	return
}
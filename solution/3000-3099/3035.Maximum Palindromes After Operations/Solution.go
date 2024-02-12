func maxPalindromesAfterOperations(words []string) (ans int) {
	var s, mask int
	for _, w := range words {
		s += len(w)
		for _, c := range w {
			mask ^= 1 << (c - 'a')
		}
	}
	s -= bits.OnesCount(uint(mask))
	sort.Slice(words, func(i, j int) bool {
		return len(words[i]) < len(words[j])
	})
	for _, w := range words {
		s -= len(w) / 2 * 2
		if s < 0 {
			break
		}
		ans++
	}
	return
}
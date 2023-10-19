func canBeTypedWords(text string, brokenLetters string) (ans int) {
	s := [26]bool{}
	for _, c := range brokenLetters {
		s[c-'a'] = true
	}
	for _, w := range strings.Split(text, " ") {
		for _, c := range w {
			if s[c-'a'] {
				ans--
				break
			}
		}
		ans++
	}
	return
}
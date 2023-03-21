func vowelStrings(words []string, left int, right int) (ans int) {
	check := func(c byte) bool {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
	}
	for _, w := range words[left : right+1] {
		if check(w[0]) && check(w[len(w)-1]) {
			ans++
		}
	}
	return
}
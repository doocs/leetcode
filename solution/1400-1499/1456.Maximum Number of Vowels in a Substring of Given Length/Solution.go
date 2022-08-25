func maxVowels(s string, k int) int {
	isVowel := func(c byte) bool {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
	}
	t := 0
	for i := 0; i < k; i++ {
		if isVowel(s[i]) {
			t++
		}
	}
	ans := t
	for i := k; i < len(s); i++ {
		if isVowel(s[i]) {
			t++
		}
		if isVowel(s[i-k]) {
			t--
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
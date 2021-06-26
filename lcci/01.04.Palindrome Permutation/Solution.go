func canPermutePalindrome(s string) bool {
	m := make(map[rune]bool)
	count := 0
	for _, r := range s {
		if m[r] {
			m[r] = false
			count--
		} else {
			m[r] = true
			count++
		}
	}
	return count <= 1
}

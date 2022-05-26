func CheckPermutation(s1 string, s2 string) bool {
	freq := make(map[rune]int)
	for _, r := range s1 {
		freq[r]++
	}
	for _, r := range s2 {
		if freq[r] == 0 {
			return false
		}
		freq[r]--
	}
	for _, v := range freq {
		if v != 0 {
			return false
		}
	}
	return true
}

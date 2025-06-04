func answerString(word string, numFriends int) string {
	if numFriends == 1 {
		return word
	}
	s := lastSubstring(word)
	return s[:min(len(s), len(word)-numFriends+1)]
}

func lastSubstring(s string) string {
	n := len(s)
	i, j, k := 0, 1, 0
	for j+k < n {
		if s[i+k] == s[j+k] {
			k++
		} else if s[i+k] < s[j+k] {
			i += k + 1
			k = 0
			if i >= j {
				j = i + 1
			}
		} else {
			j += k + 1
			k = 0
		}
	}
	return s[i:]
}
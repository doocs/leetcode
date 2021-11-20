func shortestCompletingWord(licensePlate string, words []string) string {
	count := func(word string) []int {
		counter := make([]int, 26)
		for _, c := range word {
			if unicode.IsLetter(c) {
				counter[c-'a']++
			}
		}
		return counter
	}

	check := func(cnt1, cnt2 []int) bool {
		for i := 0; i < 26; i++ {
			if cnt1[i] > cnt2[i] {
				return false
			}
		}
		return true
	}

	counter := count(strings.ToLower(licensePlate))
	var ans string
	n := 16
	for _, word := range words {
		if n <= len(word) {
			continue
		}
		t := count(word)
		if check(counter, t) {
			n = len(word)
			ans = word
		}
	}
	return ans
}
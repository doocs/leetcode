func countCharacters(words []string, chars string) int {
	counter := count(chars)
	ans := 0
	for _, word := range words {
		cnt := count(word)
		if check(counter, cnt) {
			ans += len(word)
		}
	}
	return ans
}

func count(s string) []int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	return counter
}

func check(cnt1, cnt2 []int) bool {
	for i := 0; i < 26; i++ {
		if cnt1[i] < cnt2[i] {
			return false
		}
	}
	return true
}
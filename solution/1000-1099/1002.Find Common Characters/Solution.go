func commonChars(words []string) []string {
	freq := make([]int, 26)
	for i := 0; i < 26; i++ {
		freq[i] = 10000
	}
	for _, word := range words {
		t := make([]int, 26)
		for _, c := range word {
			t[c-'a']++
		}
		for i := 0; i < 26; i++ {
			freq[i] = min(freq[i], t[i])
		}
	}
	var res []string
	for i := 0; i < 26; i++ {
		for j := 0; j < freq[i]; j++ {
			res = append(res, string('a'+i))
		}
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
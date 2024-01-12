func countWords(words1 []string, words2 []string) (ans int) {
	cnt1 := map[string]int{}
	cnt2 := map[string]int{}
	for _, w := range words1 {
		cnt1[w]++
	}
	for _, w := range words2 {
		cnt2[w]++
	}
	for w, v := range cnt1 {
		if v == 1 && cnt2[w] == 1 {
			ans++
		}
	}
	return
}
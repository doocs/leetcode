func closeStrings(word1 string, word2 string) bool {
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for _, c := range word1 {
		cnt1[c-'a']++
	}
	for _, c := range word2 {
		cnt2[c-'a']++
	}
	for i, v := range cnt1 {
		if (v > 0 && cnt2[i] == 0) || (v == 0 && cnt2[i] > 0) {
			return false
		}
	}
	sort.Ints(cnt1)
	sort.Ints(cnt2)
	for i, v := range cnt1 {
		if v != cnt2[i] {
			return false
		}
	}
	return true
}
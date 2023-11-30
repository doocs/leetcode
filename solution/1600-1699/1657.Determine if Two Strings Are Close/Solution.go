func closeStrings(word1 string, word2 string) bool {
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for _, c := range word1 {
		cnt1[c-'a']++
	}
	for _, c := range word2 {
		cnt2[c-'a']++
	}
	if !slices.EqualFunc(cnt1, cnt2, func(v1, v2 int) bool { return (v1 == 0) == (v2 == 0) }) {
		return false
	}
	sort.Ints(cnt1)
	sort.Ints(cnt2)
	return slices.Equal(cnt1, cnt2)
}
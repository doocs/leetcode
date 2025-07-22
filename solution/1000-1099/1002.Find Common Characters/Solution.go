func commonChars(words []string) (ans []string) {
	cnt := make([]int, 26)
	for i := range cnt {
		cnt[i] = 20000
	}
	for _, w := range words {
		t := make([]int, 26)
		for _, c := range w {
			t[c-'a']++
		}
		for i := 0; i < 26; i++ {
			cnt[i] = min(cnt[i], t[i])
		}
	}
	for i := 0; i < 26; i++ {
		for j := 0; j < cnt[i]; j++ {
			ans = append(ans, string('a'+rune(i)))
		}
	}
	return ans
}

func getValidT9Words(num string, words []string) (ans []string) {
	s := "22233344455566677778889999"
	d := [26]rune{}
	for i, c := range s {
		d[i] = c
	}
	for _, w := range words {
		ok := true
		for i, c := range w {
			if d[c-'a'] != rune(num[i]) {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, w)
		}
	}
	return
}
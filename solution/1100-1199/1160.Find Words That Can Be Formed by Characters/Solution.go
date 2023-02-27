func countCharacters(words []string, chars string) (ans int) {
	cnt := [26]int{}
	for _, c := range chars {
		cnt[c-'a']++
	}
	for _, w := range words {
		wc := [26]int{}
		ok := true
		for _, c := range w {
			c -= 'a'
			wc[c]++
			if wc[c] > cnt[c] {
				ok = false
				break
			}
		}
		if ok {
			ans += len(w)
		}
	}
	return
}
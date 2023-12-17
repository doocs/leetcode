func shortestCompletingWord(licensePlate string, words []string) (ans string) {
	cnt := [26]int{}
	for _, c := range licensePlate {
		if unicode.IsLetter(c) {
			cnt[unicode.ToLower(c)-'a']++
		}
	}
	for _, w := range words {
		if len(ans) > 0 && len(ans) <= len(w) {
			continue
		}
		t := [26]int{}
		for _, c := range w {
			t[c-'a']++
		}
		ok := true
		for i, v := range cnt {
			if t[i] < v {
				ok = false
				break
			}
		}
		if ok {
			ans = w
		}
	}
	return
}
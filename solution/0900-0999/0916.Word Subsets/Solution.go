func wordSubsets(words1 []string, words2 []string) (ans []string) {
	cnt := [26]int{}
	for _, b := range words2 {
		t := [26]int{}
		for _, c := range b {
			t[c-'a']++
		}
		for i := range cnt {
			cnt[i] = max(cnt[i], t[i])
		}
	}
	for _, a := range words1 {
		t := [26]int{}
		for _, c := range a {
			t[c-'a']++
		}
		ok := true
		for i, v := range cnt {
			if v > t[i] {
				ok = false
				break
			}
		}
		if ok {
			ans = append(ans, a)
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
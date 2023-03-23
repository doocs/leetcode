func commonChars(words []string) (ans []string) {
	cnt := [26]int{}
	for i := range cnt {
		cnt[i] = 1 << 30
	}
	for _, w := range words {
		ccnt := [26]int{}
		for _, c := range w {
			ccnt[c-'a']++
		}
		for i, v := range cnt {
			cnt[i] = min(v, ccnt[i])
		}
	}
	for i, v := range cnt {
		for v > 0 {
			ans = append(ans, string(i+'a'))
			v--
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
func rearrangeCharacters(s string, target string) int {
	var cnt1, cnt2 [26]int
	for _, c := range s {
		cnt1[c-'a']++
	}
	for _, c := range target {
		cnt2[c-'a']++
	}
	ans := 100
	for i, v := range cnt2 {
		if v > 0 {
			ans = min(ans, cnt1[i]/v)
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
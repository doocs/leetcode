func rearrangeCharacters(s string, target string) int {
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for _, c := range s {
		cnt1[c-'a']++
	}
	for _, c := range target {
		cnt2[c-'a']++
	}
	ans := 100
	for i, v := range cnt2 {
		if v <= 0 {
			continue
		}
		if cnt1[i] < v {
			return 0
		}
		ans = min(ans, cnt1[i]/v)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
func maxDifference(s string) int {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	a, b := 0, 1<<30
	for _, v := range cnt {
		if v%2 == 1 {
			a = max(a, v)
		} else if v > 0 {
			b = min(b, v)
		}
	}
	return a - b
}

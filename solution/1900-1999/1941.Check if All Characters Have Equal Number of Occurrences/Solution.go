func areOccurrencesEqual(s string) bool {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	x := 0
	for _, v := range cnt {
		if v > 0 {
			if x == 0 {
				x = v
			} else if x != v {
				return false
			}
		}
	}
	return true
}
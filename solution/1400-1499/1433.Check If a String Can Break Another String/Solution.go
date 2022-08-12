func checkIfCanBreak(s1 string, s2 string) bool {
	cs1 := []byte(s1)
	cs2 := []byte(s2)
	sort.Slice(cs1, func(i, j int) bool { return cs1[i] < cs1[j] })
	sort.Slice(cs2, func(i, j int) bool { return cs2[i] < cs2[j] })
	check := func(cs1, cs2 []byte) bool {
		for i := range cs1 {
			if cs1[i] < cs2[i] {
				return false
			}
		}
		return true
	}
	return check(cs1, cs2) || check(cs2, cs1)
}
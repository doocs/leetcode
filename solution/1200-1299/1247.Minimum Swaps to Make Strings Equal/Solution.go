func minimumSwap(s1 string, s2 string) int {
	xy, yx := 0, 0
	for i, _ := range s1 {
		if s1[i] < s2[i] {
			xy++
		}
		if s1[i] > s2[i] {
			yx++
		}
	}
	if (xy+yx)%2 != 0 {
		return -1
	}
	if xy%2 == 0 {
		return (xy + yx) / 2
	}
	return (xy+yx)/2 + 1
}
func minimumChairs(s string) int {
	cnt, left := 0, 0
	for _, c := range s {
		if c == 'E' {
			if left > 0 {
				left--
			} else {
				cnt++
			}
		} else {
			left++
		}
	}
	return cnt
}
func secondHighest(s string) int {
	mask := 0
	for _, c := range s {
		if c >= '0' && c <= '9' {
			mask |= 1 << int(c-'0')
		}
	}
	for i, cnt := 9, 0; i >= 0; i-- {
		if mask>>i&1 == 1 {
			cnt++
			if cnt == 2 {
				return i
			}
		}
	}
	return -1
}
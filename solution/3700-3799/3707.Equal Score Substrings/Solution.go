func scoreBalance(s string) bool {
	var l, r int
	for _, c := range s {
		x := int(c-'a') + 1
		r += x
	}
	for _, c := range s[:len(s)-1] {
		x := int(c-'a') + 1
		l += x
		r -= x
		if l == r {
			return true
		}
	}
	return false
}

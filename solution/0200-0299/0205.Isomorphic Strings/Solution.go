func isIsomorphic(s string, t string) bool {
	d1, d2 := make([]int, 256), make([]int, 256)
	for i, a := range s {
		b := t[i]
		if d1[a] != d2[b] {
			return false
		}
		d1[a], d2[b] = i+1, i+1
	}
	return true
}
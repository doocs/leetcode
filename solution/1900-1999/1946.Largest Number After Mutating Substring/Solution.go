func maximumNumber(num string, change []int) string {
	s := []byte(num)
	changed := false
	for i, c := range num {
		d := byte('0' + change[c-'0'])
		if changed && d < s[i] {
			break
		}
		if d > s[i] {
			s[i] = d
			changed = true
		}
	}
	return string(s)
}

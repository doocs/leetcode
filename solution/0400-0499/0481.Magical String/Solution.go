func magicalString(n int) int {
	s := []byte("1221121")
	i := 5
	for len(s) < n {
		c := s[len(s)-1]
		if s[i] == '1' {
			if c == '1' {
				s = append(s, '2')
			} else {
				s = append(s, '1')
			}
		} else {
			if c == '1' {
				s = append(s, '2')
				s = append(s, '2')
			} else {
				s = append(s, '1')
				s = append(s, '1')
			}
		}
		i++
	}
	return bytes.Count(s[:n], []byte("1"))
}
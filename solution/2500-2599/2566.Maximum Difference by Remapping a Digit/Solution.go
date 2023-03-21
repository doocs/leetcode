func minMaxDifference(num int) int {
	s := []byte(strconv.Itoa(num))
	first := s[0]
	for i := range s {
		if s[i] == first {
			s[i] = '0'
		}
	}
	mi, _ := strconv.Atoi(string(s))
	t := []byte(strconv.Itoa(num))
	for i := range t {
		if t[i] != '9' {
			second := t[i]
			for j := i; j < len(t); j++ {
				if t[j] == second {
					t[j] = '9'
				}
			}
			mx, _ := strconv.Atoi(string(t))
			return mx - mi
		}
	}
	return num - mi
}
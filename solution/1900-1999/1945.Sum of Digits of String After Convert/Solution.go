func getLucky(s string, k int) int {
	var t strings.Builder
	for _, c := range s {
		t.WriteString(strconv.Itoa(int(c - 'a' + 1)))
	}
	s = t.String()
	for k > 0 {
		k--
		t := 0
		for _, c := range s {
			t += int(c - '0')
		}
		s = strconv.Itoa(t)
	}
	ans, _ := strconv.Atoi(s)
	return ans
}
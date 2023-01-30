func digitSum(s string, k int) string {
	for len(s) > k {
		t := &strings.Builder{}
		n := len(s)
		for i := 0; i < n; i += k {
			x := 0
			for j := i; j < i+k && j < n; j++ {
				x += int(s[j] - '0')
			}
			t.WriteString(strconv.Itoa(x))
		}
		s = t.String()
	}
	return s
}
func findDifferentBinaryString(nums []string) string {
	count := func() []bool {
		s := make([]bool, 17)
		for _, num := range nums {
			t := 0
			for _, c := range num {
				if c == '1' {
					t++
				}
			}
			s[t] = true
		}
		return s
	}
	s := count()
	for i, n := 0, len(nums); i <= n; i++ {
		if !s[i] {
			return strings.Repeat("1", i) + strings.Repeat("0", n-i)
		}
	}
	return ""
}
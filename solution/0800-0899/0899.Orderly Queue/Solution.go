func orderlyQueue(s string, k int) string {
	if k == 1 {
		ans := s
		for i := 0; i < len(s)-1; i++ {
			s = s[1:] + s[:1]
			if s < ans {
				ans = s
			}
		}
		return ans
	}
	t := []byte(s)
	sort.Slice(t, func(i, j int) bool { return t[i] < t[j] })
	return string(t)
}
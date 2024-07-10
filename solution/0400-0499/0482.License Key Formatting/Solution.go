func licenseKeyFormatting(s string, k int) string {
	n := len(s)
	cnt := (n - strings.Count(s, "-")) % k
	if cnt == 0 {
		cnt = k
	}

	var ans strings.Builder
	for i := 0; i < n; i++ {
		c := s[i]
		if c == '-' {
			continue
		}
		if cnt == 0 {
			cnt = k
			ans.WriteByte('-')
		}
		ans.WriteRune(unicode.ToUpper(rune(c)))
		cnt--
	}

	return ans.String()
}
func licenseKeyFormatting(s string, k int) string {
	s = strings.ReplaceAll(s, "-", "")
	cnt := len(s) % k
	if cnt == 0 {
		cnt = k
	}
	t := 0
	res := []byte{}
	for i, c := range s {
		res = append(res, byte(unicode.ToUpper(c)))
		t++
		if t == cnt {
			t = 0
			cnt = k
			if i != len(s)-1 {
				res = append(res, byte('-'))
			}
		}
	}
	return string(res)
}
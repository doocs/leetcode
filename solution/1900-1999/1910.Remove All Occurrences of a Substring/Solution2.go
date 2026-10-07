func removeOccurrences(s string, part string) string {
	m := len(part)
	st := make([]byte, 0, len(s))
	for i := 0; i < len(s); i++ {
		st = append(st, s[i])
		if len(st) >= m && string(st[len(st)-m:]) == part {
			st = st[:len(st)-m]
		}
	}
	return string(st)
}

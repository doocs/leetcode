func oddString(words []string) string {
	d := map[string][]string{}
	for _, s := range words {
		m := len(s)
		cs := make([]byte, m-1)
		for i := 0; i < m-1; i++ {
			cs[i] = s[i+1] - s[i]
		}
		t := string(cs)
		d[t] = append(d[t], s)
	}
	for _, ss := range d {
		if len(ss) == 1 {
			return ss[0]
		}
	}
	return ""
}
func oddString(words []string) string {
	cnt := map[string][]string{}
	for _, w := range words {
		d := make([]byte, len(w)-1)
		for i := 0; i < len(w)-1; i++ {
			d[i] = w[i+1] - w[i]
		}
		t := string(d)
		cnt[t] = append(cnt[t], w)
	}
	for _, v := range cnt {
		if len(v) == 1 {
			return v[0]
		}
	}
	return ""
}
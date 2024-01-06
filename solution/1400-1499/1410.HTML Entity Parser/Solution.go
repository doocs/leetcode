func entityParser(text string) string {
	d := map[string]string{
		"&quot;":  "\"",
		"&apos;":  "'",
		"&amp;":   "&",
		"&gt;":    ">",
		"&lt;":    "<",
		"&frasl;": "/",
	}
	var ans strings.Builder
	i, n := 0, len(text)

	for i < n {
		found := false
		for l := 1; l < 8; l++ {
			j := i + l
			if j <= n {
				t := text[i:j]
				if val, ok := d[t]; ok {
					ans.WriteString(val)
					i = j
					found = true
					break
				}
			}
		}
		if !found {
			ans.WriteByte(text[i])
			i++
		}
	}

	return ans.String()
}
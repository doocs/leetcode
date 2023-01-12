func evaluate(s string, knowledge [][]string) string {
	d := map[string]string{}
	for _, v := range knowledge {
		d[v[0]] = v[1]
	}
	var ans strings.Builder
	for i := 0; i < len(s); i++ {
		if s[i] == '(' {
			j := i + 1
			for s[j] != ')' {
				j++
			}
			if v, ok := d[s[i+1:j]]; ok {
				ans.WriteString(v)
			} else {
				ans.WriteByte('?')
			}
			i = j
		} else {
			ans.WriteByte(s[i])
		}
	}
	return ans.String()
}
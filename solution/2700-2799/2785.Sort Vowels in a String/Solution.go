func sortVowels(s string) string {
	cs := []byte(s)
	vs := []byte{}
	for _, c := range cs {
		d := c | 32
		if d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u' {
			vs = append(vs, c)
		}
	}
	sort.Slice(vs, func(i, j int) bool { return vs[i] < vs[j] })
	j := 0
	for i, c := range cs {
		d := c | 32
		if d == 'a' || d == 'e' || d == 'i' || d == 'o' || d == 'u' {
			cs[i] = vs[j]
			j++
		}
	}
	return string(cs)
}
func compressString(S string) string {
	n := len(S)
	sb := strings.Builder{}
	for i := 0; i < n; {
		j := i + 1
		for j < n && S[j] == S[i] {
			j++
		}
		sb.WriteByte(S[i])
		sb.WriteString(strconv.Itoa(j - i))
		i = j
	}
	if t := sb.String(); len(t) < n {
		return t
	}
	return S
}
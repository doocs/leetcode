func compressString(S string) string {
	n := len(S)
	if n == 0 {
		return S
	}
	var builder strings.Builder
	pre, cnt := S[0], 1
	for i := 1; i < n; i++ {
		if S[i] != pre {
			builder.WriteByte(pre)
			builder.WriteString(strconv.Itoa(cnt))
			cnt = 1
		} else {
			cnt++
		}
		pre = S[i]
	}
	builder.WriteByte(pre)
	builder.WriteString(strconv.Itoa(cnt))
	if builder.Len() >= n {
		return S
	}
	return builder.String()
}

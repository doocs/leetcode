func decodeMessage(key string, message string) string {
	d := [128]byte{}
	d[' '] = ' '
	for i, j := 0, 0; i < len(key); i++ {
		if d[key[i]] == 0 {
			d[key[i]] = byte('a' + j)
			j++
		}
	}
	ans := make([]byte, len(message))
	for i, c := range message {
		ans[i] = d[c]
	}
	return string(ans)
}
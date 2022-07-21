func decodeMessage(key string, message string) string {
	d := map[rune]byte{}
	d[' '] = ' '
	i := 0
	lowcase := "abcdefghijklmnopqrstuvwxyz"
	for _, c := range key {
		if _, ok := d[c]; ok {
			continue
		}
		d[c] = lowcase[i]
		i++
	}
	var ans []byte
	for _, c := range message {
		ans = append(ans, d[c])
	}
	return string(ans)
}
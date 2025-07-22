func freqAlphabets(s string) string {
	var ans []byte
	for i, n := 0, len(s); i < n; {
		if i+2 < n && s[i+2] == '#' {
			num := (int(s[i])-'0')*10 + int(s[i+1]) - '0'
			ans = append(ans, byte(num+int('a')-1))
			i += 3
		} else {
			num := int(s[i]) - '0'
			ans = append(ans, byte(num+int('a')-1))
			i += 1
		}
	}
	return string(ans)
}

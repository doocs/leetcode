func maximumNumber(num string, change []int) string {
	s := []byte(num)
	for i, c := range num {
		if change[c-'0'] > int(c-'0') {
			for ; i < len(s) && change[s[i]-'0'] >= int(s[i]-'0'); i++ {
				s[i] = byte(change[s[i]-'0']) + '0'
			}
			break
		}
	}
	return string(s)
}
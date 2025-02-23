func hasSameDigits(s string) bool {
	t := []byte(s)
	n := len(t)
	for k := n - 1; k > 1; k-- {
		for i := 0; i < k; i++ {
			t[i] = (t[i]-'0'+t[i+1]-'0')%10 + '0'
		}
	}
	return t[0] == t[1]
}

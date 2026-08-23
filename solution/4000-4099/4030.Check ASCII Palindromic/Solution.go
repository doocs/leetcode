func isPalindromic(s string) bool {
	var t []byte
	for _, c := range []byte(s) {
		for i := 7; i >= 0; i-- {
			t = append(t, '0'+((c>>i)&1))
		}
	}
	for i := range t[:len(t)/2] {
		if t[i] != t[len(t)-1-i] {
			return false
		}
	}
	return true
}

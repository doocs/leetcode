func getEncryptedString(s string, k int) string {
	cs := []byte(s)
	for i := range s {
		cs[i] = s[(i+k)%len(s)]
	}
	return string(cs)
}
func checkIfPangram(sentence string) bool {
	res := 0
	for _, c := range sentence {
		res |= (1 << (c - 'a'))
		if res == 0x3ffffff {
			return true
		}
	}
	return false
}
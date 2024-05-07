func checkIfExist(arr []int) bool {
	s := map[int]bool{}
	for _, x := range arr {
		if s[x*2] || (x%2 == 0 && s[x/2]) {
			return true
		}
		s[x] = true
	}
	return false
}
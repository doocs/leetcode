func checkIfExist(arr []int) bool {
	s := map[int]bool{}
	for _, v := range arr {
		if s[v*2] || (v%2 == 0 && s[v/2]) {
			return true
		}
		s[v] = true
	}
	return false
}
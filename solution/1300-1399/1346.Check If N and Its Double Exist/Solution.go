func checkIfExist(arr []int) bool {
	m := make(map[int]int)
	for i, v := range arr {
		m[v] = i
	}
	for i, v := range arr {
		if j, ok := m[v*2]; ok && j != i {
			return true
		}
	}
	return false
}
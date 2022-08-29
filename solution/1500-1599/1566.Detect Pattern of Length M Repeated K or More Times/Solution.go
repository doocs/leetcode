func containsPattern(arr []int, m int, k int) bool {
	n := len(arr)
	for i := 0; i <= n-m*k; i++ {
		j := 0
		for ; j < m*k; j++ {
			if arr[i+j] != arr[i+(j%m)] {
				break
			}
		}
		if j == m*k {
			return true
		}
	}
	return false
}
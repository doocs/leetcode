func containsPattern(arr []int, m int, k int) bool {
	cnt, target := 0, (k-1)*m
	for i := m; i < len(arr); i++ {
		if arr[i] == arr[i-m] {
			cnt++
			if cnt == target {
				return true
			}
		} else {
			cnt = 0
		}
	}
	return false
}

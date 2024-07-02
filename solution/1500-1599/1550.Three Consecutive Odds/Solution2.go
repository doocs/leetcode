func threeConsecutiveOdds(arr []int) bool {
	for i, n := 2, len(arr); i < n; i++ {
		if arr[i-2]&arr[i-1]&arr[i]&1 == 1 {
			return true
		}
	}
	return false
}

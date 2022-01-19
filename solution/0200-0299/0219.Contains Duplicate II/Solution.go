func containsNearbyDuplicate(nums []int, k int) bool {
	mp := make(map[int]int)
	for i, v := range nums {
		if j, ok := mp[v]; ok {
			if i-j <= k {
				return true
			}
		}
		mp[v] = i
	}
	return false
}
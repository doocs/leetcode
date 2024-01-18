func canBeEqual(target []int, arr []int) bool {
	cnt := make([]int, 1001)
	for _, v := range target {
		cnt[v]++
	}
	for _, v := range arr {
		cnt[v]--
		if cnt[v] < 0 {
			return false
		}
	}
	return true
}
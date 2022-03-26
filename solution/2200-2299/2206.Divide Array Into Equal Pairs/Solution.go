func divideArray(nums []int) bool {
	cnt := make([]int, 510)
	for _, v := range nums {
		cnt[v]++
	}
	for _, v := range cnt {
		if v%2 == 1 {
			return false
		}
	}
	return true
}
func isMiddleElementUnique(nums []int) bool {
	cnt := 0
	for _, x := range nums {
		if x == nums[len(nums)/2] {
			cnt++
		}
	}
	return cnt == 1
}

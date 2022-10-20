func check(nums []int) bool {
	n := len(nums)
	cnt := 0
	for i, v := range nums {
		if v > nums[(i+1)%n] {
			cnt++
		}
	}
	return cnt <= 1
}
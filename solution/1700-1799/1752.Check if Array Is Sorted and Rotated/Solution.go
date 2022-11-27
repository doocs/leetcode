func check(nums []int) bool {
	cnt := 0
	for i, v := range nums {
		if v > nums[(i+1)%len(nums)] {
			cnt++
		}
	}
	return cnt <= 1
}
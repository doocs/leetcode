func check(nums []int) bool {
	cnt := 0
	for i, x := range nums {
		if x > nums[(i+1)%len(nums)] {
			cnt++
		}
	}
	return cnt <= 1
}
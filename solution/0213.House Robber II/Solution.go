func rob(nums []int) int {
	l := len(nums)
	if l == 0 {
		return 0
	}
	if l == 1 {
		return nums[0]
	}
	res1 := _rob(nums[:l-1])
	res2 := _rob(nums[1:])
	if res1 < res2 {
		return res2
	}
	return res1
}

func _rob(nums []int) int {
	x, y := 0, 0
	for _, n := range nums {
		x, y = y, x+n
		if x > y {
			y = x
		}
	}
	return y
}

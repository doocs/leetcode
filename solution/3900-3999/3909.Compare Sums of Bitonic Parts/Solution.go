func compareBitonicSums(nums []int) int {
	var l, r int64
	l = int64(nums[0])
	r = 0
	for _, x := range nums {
		r += int64(x)
	}
	for i := 1; i < len(nums); i++ {
		if nums[i-1] > nums[i] {
			break
		}
		l += int64(nums[i])
		r -= int64(nums[i-1])
	}
	if l == r {
		return -1
	}
	if l > r {
		return 0
	}
	return 1
}

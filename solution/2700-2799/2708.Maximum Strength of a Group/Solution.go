func maxStrength(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	if n == 1 {
		return int64(nums[0])
	}
	if nums[1] == 0 && nums[n-1] == 0 {
		return 0
	}
	ans := int64(1)
	for i := 0; i < n; i++ {
		if nums[i] < 0 && i+1 < n && nums[i+1] < 0 {
			ans *= int64(nums[i] * nums[i+1])
			i++
		} else if nums[i] > 0 {
			ans *= int64(nums[i])
		}
	}
	return ans
}
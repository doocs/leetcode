func maxAscendingSum(nums []int) int {
	res, cur := 0, nums[0]
	for i := 1; i < len(nums); i++ {
		if nums[i] > nums[i-1] {
			cur += nums[i]
		} else {
			if res < cur {
				res = cur
			}
			cur = nums[i]
		}
	}
	if res < cur {
		res = cur
	}
	return res
}
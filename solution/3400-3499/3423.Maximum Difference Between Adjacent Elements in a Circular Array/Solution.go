func maxAdjacentDistance(nums []int) int {
	ans := abs(nums[0] - nums[len(nums)-1])
	for i := 1; i < len(nums); i++ {
		ans = max(ans, abs(nums[i]-nums[i-1]))
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

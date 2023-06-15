func countHillValley(nums []int) int {
	ans := 0
	for i, j := 1, 0; i < len(nums)-1; i++ {
		if nums[i] == nums[i+1] {
			continue
		}
		if nums[i] > nums[j] && nums[i] > nums[i+1] {
			ans++
		}
		if nums[i] < nums[j] && nums[i] < nums[i+1] {
			ans++
		}
		j = i
	}
	return ans
}
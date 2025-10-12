func longestSubarray(nums []int) int {
	f := 2
	ans := f
	for i := 2; i < len(nums); i++ {
		if nums[i] == nums[i-1]+nums[i-2] {
			f++
			ans = max(ans, f)
		} else {
			f = 2
		}
	}
	return ans
}

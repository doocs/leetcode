func countSubarrays(nums []int) (ans int) {
	for i := 1; i+1 < len(nums); i++ {
		if (nums[i-1]+nums[i+1])*2 == nums[i] {
			ans++
		}
	}
	return
}

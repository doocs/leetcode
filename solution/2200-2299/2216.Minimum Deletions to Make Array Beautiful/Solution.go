func minDeletion(nums []int) int {
	n := len(nums)
	ans := 0
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			ans++
		} else {
			i++
		}
	}
	if (n-ans)%2 == 1 {
		ans++
	}
	return ans
}
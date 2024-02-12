func countMatchingSubarrays(nums []int, pattern []int) int {
	n := len(nums)
	m := len(pattern)
	count := 0
	for i := 0; i <= n-m-1; i++ {
		flag := true 
		for j := 0; j < m; j++ {
			if (pattern[j] == 1 && nums[i+j+1] <= nums[i+j]) ||
				(pattern[j] == 0 && nums[i+j+1] != nums[i+j]) ||
				(pattern[j] == -1 && nums[i+j+1] >= nums[i+j]) {
				flag = false
				break
			}
		}
		if flag {
			count++
		}
	}
	return count
}

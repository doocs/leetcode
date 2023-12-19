func nextPalindrome(num string) string {
	nums := []byte(num)
	n := len(nums)
	if !nextPermutation(nums) {
		return ""
	}
	for i := 0; i < n/2; i++ {
		nums[n-1-i] = nums[i]
	}
	return string(nums)
}

func nextPermutation(nums []byte) bool {
	n := len(nums) / 2
	i := n - 2
	for i >= 0 && nums[i] >= nums[i+1] {
		i--
	}
	if i < 0 {
		return false
	}
	j := n - 1
	for j >= 0 && nums[j] <= nums[i] {
		j--
	}
	nums[i], nums[j] = nums[j], nums[i]
	for i, j = i+1, n-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	return true
}
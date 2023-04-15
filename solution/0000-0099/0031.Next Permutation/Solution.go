func nextPermutation(nums []int) {
	n := len(nums)
	i := n - 2
	for ; i >= 0 && nums[i] >= nums[i+1]; i-- {
	}
	if i >= 0 {
		for j := n - 1; j > i; j-- {
			if nums[j] > nums[i] {
				nums[i], nums[j] = nums[j], nums[i]
				break
			}
		}
	}
	for j, k := i+1, n-1; j < k; j, k = j+1, k-1 {
		nums[j], nums[k] = nums[k], nums[j]
	}
}
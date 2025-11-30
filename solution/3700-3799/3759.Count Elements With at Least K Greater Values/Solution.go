func countElements(nums []int, k int) int {
	n := len(nums)
	if k == 0 {
		return n
	}
	sort.Ints(nums)
	ans := 0
	for i := 0; i < n-k; i++ {
		if nums[n-k] > nums[i] {
			ans++
		}
	}
	return ans
}

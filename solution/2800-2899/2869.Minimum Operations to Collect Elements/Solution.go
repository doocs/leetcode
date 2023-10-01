func minOperations(nums []int, k int) int {
	isAdded := make([]bool, k)
	count := 0
	n := len(nums)
	for i := n - 1; ; i-- {
		if nums[i] > k || isAdded[nums[i]-1] {
			continue
		}
		isAdded[nums[i]-1] = true
		count++
		if count == k {
			return n - i
		}
	}
}
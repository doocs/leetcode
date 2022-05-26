func sortedSquares(nums []int) []int {
	n := len(nums)
	res := make([]int, n)
	for i, j, k := 0, n-1, n-1; i <= j; {
		if nums[i]*nums[i] > nums[j]*nums[j] {
			res[k] = nums[i] * nums[i]
			i++
		} else {
			res[k] = nums[j] * nums[j]
			j--
		}
		k--
	}
	return res
}
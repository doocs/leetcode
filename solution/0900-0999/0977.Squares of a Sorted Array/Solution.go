func sortedSquares(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i, j, k := 0, n-1, n-1; i <= j; k-- {
		a := nums[i] * nums[i]
		b := nums[j] * nums[j]
		if a > b {
			ans[k] = a
			i++
		} else {
			ans[k] = b
			j--
		}
	}
	return ans
}
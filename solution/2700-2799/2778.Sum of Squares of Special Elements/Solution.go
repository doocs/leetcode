func sumOfSquares(nums []int) (ans int) {
	n := len(nums)
	for i, x := range nums {
		if n%(i+1) == 0 {
			ans += x * x
		}
	}
	return
}
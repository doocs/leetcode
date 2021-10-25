func singleNumber(nums []int) []int {
	eor := 0
	for _, x := range nums {
		eor ^= x
	}
	lowbit := eor & (-eor)
	ans := make([]int, 2)
	for _, x := range nums {
		if (x & lowbit) == 0 {
			ans[0] ^= x
		}
	}
	ans[1] = eor ^ ans[0]
	return ans
}
func binarySearchableNumbers(nums []int) (ans int) {
	n := len(nums)
	ok := make([]int, n)
	for i := range ok {
		ok[i] = 1
	}
	mx, mi := -1000000, 1000000
	for i, x := range nums {
		if x < mx {
			ok[i] = 0
		} else {
			mx = x
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] > mi {
			ok[i] = 0
		} else {
			mi = nums[i]
		}
		ans += ok[i]
	}
	return
}
func rotate(nums []int, k int) {
	n := len(nums)
	k %= n
	reverse := func(i, j int) {
		for ; i < j; i, j = i+1, j-1 {
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	reverse(0, n-1)
	reverse(0, k-1)
	reverse(k, n-1)
}
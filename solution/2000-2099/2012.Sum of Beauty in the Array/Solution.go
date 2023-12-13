func sumOfBeauties(nums []int) (ans int) {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]
	for i := n - 2; i > 0; i-- {
		right[i] = min(right[i+1], nums[i])
	}
	for i, l := 1, nums[0]; i < n-1; i++ {
		r := right[i+1]
		if l < nums[i] && nums[i] < r {
			ans += 2
		} else if nums[i-1] < nums[i] && nums[i] < nums[i+1] {
			ans++
		}
		l = max(l, nums[i])
	}
	return
}
func rearrangeArray(nums []int) []int {
	sort.Ints(nums)
	n := len(nums)
	m := (n + 1) >> 1
	var ans []int
	for i := 0; i < m; i++ {
		ans = append(ans, nums[i])
		if i+m < n {
			ans = append(ans, nums[i+m])
		}
	}
	return ans
}
func arrayNesting(nums []int) int {
	n := len(nums)
	vis := make([]bool, n)
	ans := 0
	for i := 0; i < n; i++ {
		if vis[i] {
			continue
		}
		cur, m := nums[i], 1
		vis[cur] = true
		for nums[cur] != nums[i] {
			cur = nums[cur]
			m++
			vis[cur] = true
		}
		if m > ans {
			ans = m
		}
	}
	return ans
}
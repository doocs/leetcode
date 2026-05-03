func minCost(nums []int, queries [][]int) []int {
	n := len(nums)
	s1 := make([]int, n)
	s2 := make([]int, n)
	for i := 1; i < n; i++ {
		c1 := 1
		if i > 1 && nums[i-1]-nums[i-2] <= nums[i]-nums[i-1] {
			c1 = nums[i] - nums[i-1]
		}
		c2 := 1
		if i < n-1 && nums[i]-nums[i-1] > nums[i+1]-nums[i] {
			c2 = nums[i] - nums[i-1]
		}
		s1[i] = s1[i-1] + c1
		s2[i] = s2[i-1] + c2
	}
	m := len(queries)
	ans := make([]int, m)
	for i := 0; i < m; i++ {
		l := queries[i][0]
		r := queries[i][1]
		if l < r {
			ans[i] = s1[r] - s1[l]
		} else {
			ans[i] = s2[l] - s2[r]
		}
	}
	return ans
}

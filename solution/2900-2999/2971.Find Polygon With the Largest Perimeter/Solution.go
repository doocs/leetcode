func largestPerimeter(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	ans := -1
	for k := 3; k <= n; k++ {
		if s[k-1] > nums[k-1] {
			ans = max(ans, s[k])
		}
	}
	return int64(ans)
}
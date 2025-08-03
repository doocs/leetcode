func minRemoval(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	cnt := 0
	for i := 0; i < n; i++ {
		j := n
		if int64(nums[i])*int64(k) <= int64(nums[n-1]) {
			target := int64(nums[i])*int64(k) + 1
			j = sort.Search(n, func(x int) bool {
				return int64(nums[x]) >= target
			})
		}
		cnt = max(cnt, j-i)
	}
	return n - cnt
}

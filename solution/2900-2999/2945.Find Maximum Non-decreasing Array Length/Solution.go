func findMaximumLength(nums []int) int {
	n := len(nums)
	f := make([]int, n+1)
	pre := make([]int, n+2)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i := 1; i <= n; i++ {
		pre[i] = max(pre[i], pre[i-1])
		f[i] = f[pre[i]] + 1
		j := sort.SearchInts(s, s[i]*2-s[pre[i]])
		pre[j] = max(pre[j], i)
	}
	return f[n]
}
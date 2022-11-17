func waysToSplit(nums []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(nums)
	s := make([]int, n)
	s[0] = nums[0]
	for i := 1; i < n; i++ {
		s[i] = s[i-1] + nums[i]
	}
	for i := 0; i < n-2; i++ {
		j := sort.Search(n-1, func(h int) bool { return h > i && s[h] >= (s[i]<<1) })
		k := sort.Search(n-1, func(h int) bool { return h >= j && s[h] > (s[n-1]+s[i])>>1 })
		ans = (ans + k - j) % mod
	}
	return
}
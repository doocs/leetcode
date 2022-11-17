func sumSubseqWidths(nums []int) (ans int) {
	const mod int = 1e9 + 7
	sort.Ints(nums)
	p, n := 1, len(nums)
	for i, v := range nums {
		ans = (ans + (v-nums[n-i-1])*p + mod) % mod
		p = (p << 1) % mod
	}
	return
}
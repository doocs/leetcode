func sumOfPower(nums []int) (ans int) {
	const mod = 1e9 + 7
	sort.Ints(nums)
	p := 0
	for i := len(nums) - 1; i >= 0; i-- {
		x := nums[i]
		ans = (ans + (x*x%mod)*x) % mod
		ans = (ans + x*p%mod) % mod
		p = (p*2 + x*x%mod) % mod
	}
	return
}
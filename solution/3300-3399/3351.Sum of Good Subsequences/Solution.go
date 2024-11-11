func sumOfGoodSubsequences(nums []int) (ans int) {
	mod := int(1e9 + 7)
	mx := slices.Max(nums)

	f := make([]int, mx+1)
	g := make([]int, mx+1)

	for _, x := range nums {
		f[x] += x
		g[x] += 1

		if x > 0 {
			f[x] = (f[x] + f[x-1] + g[x-1]*x%mod) % mod
			g[x] = (g[x] + g[x-1]) % mod
		}

		if x+1 <= mx {
			f[x] = (f[x] + f[x+1] + g[x+1]*x%mod) % mod
			g[x] = (g[x] + g[x+1]) % mod
		}
	}

	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}

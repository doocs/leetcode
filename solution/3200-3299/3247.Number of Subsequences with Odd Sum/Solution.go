func subsequenceCount(nums []int) int {
	mod := int(1e9 + 7)
	f := [2]int{}
	for _, x := range nums {
		g := [2]int{}
		if x%2 == 1 {
			g[0] = (f[0] + f[1]) % mod
			g[1] = (f[0] + f[1] + 1) % mod
		} else {
			g[0] = (f[0] + f[0] + 1) % mod
			g[1] = (f[1] + f[1]) % mod
		}
		f = g
	}
	return f[1]
}

func subsequencePairCount(nums []int) int {
	const mod = 1_000_000_007
	m := slices.Max(nums)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	f[0][0] = 1
	gcd := func(a, b int) int {
		for b != 0 {
			a, b = b, a%b
		}
		return a
	}
	for _, x := range nums {
		g := make([][]int, m+1)
		for i := range g {
			g[i] = make([]int, m+1)
		}
		for j := 0; j <= m; j++ {
			for k := 0; k <= m; k++ {
				if f[j][k] == 0 {
					continue
				}
				v := f[j][k]
				g[j][k] = (g[j][k] + v) % mod
				nj := gcd(x, j)
				g[nj][k] = (g[nj][k] + v) % mod
				nk := gcd(x, k)
				g[j][nk] = (g[j][nk] + v) % mod
			}
		}
		f = g
	}
	ans := 0
	for i := 0; i <= m; i++ {
		ans += f[i][i]
	}
	return (ans - 1 + mod) % mod
}

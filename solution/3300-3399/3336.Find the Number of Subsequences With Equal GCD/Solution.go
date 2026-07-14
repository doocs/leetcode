func subsequencePairCount(nums []int) int {
	const mod = 1_000_000_007
	n := len(nums)
	m := slices.Max(nums)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, m+1)
		for j := range f[i] {
			f[i][j] = make([]int, m+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var gcd func(int, int) int
	gcd = func(a, b int) int {
		if b == 0 {
			return a
		}
		return gcd(b, a%b)
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 {
			if j == k {
				return 1
			}
			return 0
		}
		res := &f[i][j][k]
		if *res < 0 {
			x := nums[i]
			*res = ((dfs(i-1, j, k)+
				dfs(i-1, gcd(x, j), k))%mod +
				dfs(i-1, j, gcd(x, k))) % mod
		}
		return *res
	}
	return (dfs(n-1, 0, 0) - 1 + mod) % mod
}

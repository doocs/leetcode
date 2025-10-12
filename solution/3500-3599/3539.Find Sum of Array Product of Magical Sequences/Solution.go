const N = 31
const MOD = 1_000_000_007

var f [N]int64
var g [N]int64

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < N; i++ {
		f[i] = f[i-1] * int64(i) % MOD
		g[i] = qpow(f[i], MOD-2)
	}
}

func qpow(a, k int64) int64 {
	res := int64(1)
	for k > 0 {
		if k&1 == 1 {
			res = res * a % MOD
		}
		a = a * a % MOD
		k >>= 1
	}
	return res
}

func comb(m, n int) int64 {
	if n < 0 || n > m {
		return 0
	}
	return f[m] * g[n] % MOD * g[m-n] % MOD
}

func magicalSum(m int, k int, nums []int) int {
	n := len(nums)
	dp := make([][][][]int64, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([][][]int64, m+1)
		for j := 0; j <= m; j++ {
			dp[i][j] = make([][]int64, k+1)
			for l := 0; l <= k; l++ {
				dp[i][j][l] = make([]int64, N)
				for s := 0; s < N; s++ {
					dp[i][j][l][s] = -1
				}
			}
		}
	}

	var dfs func(i, j, k, st int) int64
	dfs = func(i, j, k, st int) int64 {
		if k < 0 || (i == n && j > 0) {
			return 0
		}
		if i == n {
			for st > 0 {
				k -= st & 1
				st >>= 1
			}
			if k == 0 {
				return 1
			}
			return 0
		}
		if dp[i][j][k][st] != -1 {
			return dp[i][j][k][st]
		}
		res := int64(0)
		for t := 0; t <= j; t++ {
			nt := t + st
			nk := k - (nt & 1)
			p := qpow(int64(nums[i]), int64(t))
			tmp := comb(j, t) * p % MOD * dfs(i+1, j-t, nk, nt>>1) % MOD
			res = (res + tmp) % MOD
		}
		dp[i][j][k][st] = res
		return res
	}

	return int(dfs(0, m, k, 0))
}

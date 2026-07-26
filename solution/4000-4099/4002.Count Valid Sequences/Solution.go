const MX = 500001
const MOD int64 = 1000000007

var f [MX]int64
var g [MX]int64

func init() {
	f[0] = 1
	g[0] = 1

	for i := 1; i < MX; i++ {
		f[i] = f[i-1] * int64(i) % MOD
		g[i] = pow(f[i], MOD-2)
	}
}

func pow(a, b int64) int64 {
	res := int64(1)
	for b > 0 {
		if b&1 == 1 {
			res = res * a % MOD
		}
		a = a * a % MOD
		b >>= 1
	}
	return res
}

func comb(n, k int) int64 {
	return f[n] * g[k] % MOD * g[n-k] % MOD
}

func countValidSequences(n int, k int) int {
	ans := comb(n-1, k-1)

	if (n+k)%2 == 0 {
		ans = (ans - comb((n+k)/2-1, k-1) + MOD) % MOD
	}

	return int(ans)
}

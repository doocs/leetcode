const MX = 1e5 + 10
const MOD = 1e9 + 7

var f [MX]int64
var g [MX]int64

func qpow(a int64, k int) int64 {
	res := int64(1)
	for k != 0 {
		if k&1 == 1 {
			res = res * a % MOD
		}
		a = a * a % MOD
		k >>= 1
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < MX; i++ {
		f[i] = f[i-1] * int64(i) % MOD
		g[i] = qpow(f[i], MOD-2)
	}
}

func comb(m, n int) int64 {
	return f[m] * g[n] % MOD * g[m-n] % MOD
}

func countGoodArrays(n int, m int, k int) int {
	ans := comb(n-1, k) * int64(m) % MOD * qpow(int64(m-1), n-k-1) % MOD
	return int(ans)
}
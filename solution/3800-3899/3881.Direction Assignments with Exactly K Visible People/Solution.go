package main

const N = 100001
const MOD = 1e9 + 7

var f = make([]int, N)
var g = make([]int, N)

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < N; i++ {
		f[i] = f[i-1] * i % MOD
		g[i] = qmi(f[i], MOD-2, MOD)
	}
}

func comb(n, k int) int {
	return f[n] * g[k] % MOD * g[n-k] % MOD
}

func countVisiblePeople(n int, pos int, k int) int {
	l, r := pos, n-pos-1
	ans := 0

	for a := 0; a <= min(k, l); a++ {
		b := k - a
		if b <= r {
			ans = (ans + 2*comb(l, a)%MOD*comb(r, b)%MOD) % MOD
		}
	}
	return ans
}

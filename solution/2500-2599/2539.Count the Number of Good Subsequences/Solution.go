const n = 1e4 + 1
const mod = 1e9 + 7

var f = make([]int, n)
var g = make([]int, n)

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
	for i := 1; i < n; i++ {
		f[i] = f[i-1] * i % mod
		g[i] = qmi(f[i], mod-2, mod)
	}
}

func comb(n, k int) int {
	return (f[n] * g[k] % mod) * g[n-k] % mod
}

func countGoodSubsequences(s string) (ans int) {
	cnt := [26]int{}
	mx := 1
	for _, c := range s {
		cnt[c-'a']++
		mx = max(mx, cnt[c-'a'])
	}
	for i := 1; i <= mx; i++ {
		x := 1
		for _, v := range cnt {
			if v >= i {
				x = (x * (comb(v, i) + 1)) % mod
			}
		}
		ans = (ans + x - 1) % mod
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
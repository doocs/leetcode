const n = 1e4 + 20
const mod = 1e9 + 7

var f = make([]int, n)
var g = make([]int, n)
var p = make([][]int, n)

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
		x := i
		for j := 2; j <= x/j; j++ {
			if x%j == 0 {
				cnt := 0
				for x%j == 0 {
					cnt++
					x /= j
				}
				p[i] = append(p[i], cnt)
			}
		}
		if x > 1 {
			p[i] = append(p[i], 1)
		}
	}
}

func comb(n, k int) int {
	return (f[n] * g[k] % mod) * g[n-k] % mod
}

func waysToFillArray(queries [][]int) (ans []int) {
	for _, q := range queries {
		n, k := q[0], q[1]
		t := 1
		for _, x := range p[k] {
			t = t * comb(x+n-1, n-1) % mod
		}
		ans = append(ans, t)
	}
	return
}
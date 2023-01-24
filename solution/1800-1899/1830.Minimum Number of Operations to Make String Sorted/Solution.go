const n = 3010
const mod = 1e9 + 7

var f = make([]int, n)
var g = make([]int, n)

func qmi(a, k int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % mod
		}
		k >>= 1
		a = a * a % mod
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < n; i++ {
		f[i] = f[i-1] * i % mod
		g[i] = qmi(f[i], mod-2)
	}
}

func makeStringSorted(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for i, c := range s {
		m := 0
		for j := int(c-'a') - 1; j >= 0; j-- {
			m += cnt[j]
		}
		t := m * f[len(s)-i-1] % mod
		for _, v := range cnt {
			t = t * g[v] % mod
		}
		ans = (ans + t + mod) % mod
		cnt[c-'a']--
	}
	return
}
const (
	mx        = 100001
	mod int64 = 1000000007
)

var pow10 = func() []int64 {
	p := make([]int64, mx)
	p[0] = 1
	for i := 1; i < mx; i++ {
		p[i] = p[i-1] * 10 % mod
	}
	return p
}()

func sumAndMultiply(s string, queries [][]int) []int {
	n := len(s)
	sumD := make([]int, n+1)
	cntN0 := make([]int, n+1)
	p := make([]int64, n+1)

	for i := 1; i <= n; i++ {
		d := int64(s[i-1] - '0')
		sumD[i] = sumD[i-1] + int(d)
		cntN0[i] = cntN0[i-1]
		if d > 0 {
			cntN0[i]++
			p[i] = (p[i-1]*10 + d) % mod
		} else {
			p[i] = p[i-1]
		}
	}

	ans := make([]int, len(queries))
	for i, q := range queries {
		l, r := q[0], q[1]
		n0 := cntN0[r+1] - cntN0[l]
		sd := int64(sumD[r+1] - sumD[l])
		x := (p[r+1] - p[l]*pow10[n0]%mod + mod) % mod
		ans[i] = int(x * sd % mod)
	}
	return ans
}

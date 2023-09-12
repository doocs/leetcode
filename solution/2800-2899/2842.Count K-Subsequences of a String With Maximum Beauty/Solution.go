func countKSubsequencesWithMaxBeauty(s string, k int) int {
	f := [26]int{}
	cnt := 0
	for _, c := range s {
		f[c-'a']++
		if f[c-'a'] == 1 {
			cnt++
		}
	}
	if cnt < k {
		return 0
	}
	vs := []int{}
	for _, x := range f {
		if x > 0 {
			vs = append(vs, x)
		}
	}
	sort.Slice(vs, func(i, j int) bool {
		return vs[i] > vs[j]
	})
	const mod int = 1e9 + 7
	ans := 1
	val := vs[k-1]
	x := 0
	for _, v := range vs {
		if v == val {
			x++
		}
	}
	for _, v := range vs {
		if v == val {
			break
		}
		k--
		ans = ans * v % mod
	}
	c := make([][]int, x+1)
	for i := range c {
		c[i] = make([]int, x+1)
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod
		}
	}
	ans = (ans * c[x][k] % mod) * qmi(val, k, mod) % mod
	return ans
}

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
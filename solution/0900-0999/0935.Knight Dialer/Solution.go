func knightDialer(n int) int {
	if n == 1 {
		return 10
	}
	f := make([]int, 10)
	for i := range f {
		f[i] = 1
	}
	mod := int(1e9) + 7
	for i := 1; i < n; i++ {
		t := make([]int, 10)
		t[0] = f[4] + f[6]
		t[1] = f[6] + f[8]
		t[2] = f[7] + f[9]
		t[3] = f[4] + f[8]
		t[4] = f[0] + f[3] + f[9]
		t[6] = f[0] + f[1] + f[7]
		t[7] = f[2] + f[6]
		t[8] = f[1] + f[3]
		t[9] = f[2] + f[4]
		for j, v := range t {
			f[j] = v % mod
		}
	}
	ans := 0
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return ans
}
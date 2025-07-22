func lengthAfterTransformations(s string, t int) int {
	const mod = 1_000_000_007
	f := make([][]int, t+1)
	for i := range f {
		f[i] = make([]int, 26)
	}

	for _, c := range s {
		f[0][c-'a']++
	}

	for i := 1; i <= t; i++ {
		f[i][0] = f[i-1][25] % mod
		f[i][1] = (f[i-1][0] + f[i-1][25]) % mod
		for j := 2; j < 26; j++ {
			f[i][j] = f[i-1][j-1] % mod
		}
	}

	ans := 0
	for j := 0; j < 26; j++ {
		ans = (ans + f[t][j]) % mod
	}
	return ans
}

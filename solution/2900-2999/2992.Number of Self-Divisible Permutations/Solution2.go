func selfDivisiblePermutationCount(n int) int {
	f := make([]int, 1<<n)
	f[0] = 1
	for mask := 0; mask < 1<<n; mask++ {
		i := bits.OnesCount(uint(mask))
		for j := 1; j <= n; j++ {
			if mask>>(j-1)&1 == 1 && (i%j == 0 || j%i == 0) {
				f[mask] += f[mask^(1<<(j-1))]
			}
		}
	}
	return f[(1<<n)-1]
}
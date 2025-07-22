func minimumTime(power []int) int64 {
	n := len(power)
	f := make([]int64, 1<<n)
	for i := range f {
		f[i] = 1e18
	}
	f[0] = 0
	for mask := 1; mask < 1<<n; mask++ {
		gain := bits.OnesCount(uint(mask))
		for i, x := range power {
			if mask>>i&1 == 1 {
				f[mask] = min(f[mask], f[mask^(1<<i)]+int64(x+gain-1)/int64(gain))
			}
		}
	}
	return f[1<<n-1]
}

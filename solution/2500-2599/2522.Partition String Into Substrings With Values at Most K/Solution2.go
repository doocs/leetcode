func minimumPartition(s string, k int) int {
	n := len(s)
	const inf = 1 << 30
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		f[i] = inf
	}
	for i := n - 1; i >= 0; i-- {
		v := 0
		for j := i; j < n; j++ {
			v = v*10 + int(s[j]-'0')
			if v > k {
				break
			}
			f[i] = min(f[i], f[j+1])
		}
		f[i]++
	}
	if f[0] < inf {
		return f[0]
	}
	return -1
}

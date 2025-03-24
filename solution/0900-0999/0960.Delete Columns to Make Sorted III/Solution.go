func minDeletionSize(strs []string) int {
	n := len(strs[0])
	f := make([]int, n)
	for i := range f {
		f[i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			ok := true
			for _, s := range strs {
				if s[j] > s[i] {
					ok = false
					break
				}
			}
			if ok {
				f[i] = max(f[i], f[j]+1)
			}
		}
	}
	return n - slices.Max(f)
}

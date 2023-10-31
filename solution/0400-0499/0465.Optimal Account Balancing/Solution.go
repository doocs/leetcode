func minTransfers(transactions [][]int) int {
	g := [12]int{}
	for _, t := range transactions {
		g[t[0]] -= t[2]
		g[t[1]] += t[2]
	}
	nums := []int{}
	for _, x := range g {
		if x != 0 {
			nums = append(nums, x)
		}
	}
	m := len(nums)
	f := make([]int, 1<<m)
	for i := 1; i < 1<<m; i++ {
		f[i] = 1 << 29
		s := 0
		for j, x := range nums {
			if i>>j&1 == 1 {
				s += x
			}
		}
		if s == 0 {
			f[i] = bits.OnesCount(uint(i)) - 1
			for j := (i - 1) & i; j > 0; j = (j - 1) & i {
				f[i] = min(f[i], f[j]+f[i^j])
			}
		}
	}
	return f[1<<m-1]
}
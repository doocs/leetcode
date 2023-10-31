func minimumIncompatibility(nums []int, k int) int {
	n := len(nums)
	m := n / k
	const inf = 1 << 30
	f := make([]int, 1<<n)
	g := make([]int, 1<<n)
	for i := range g {
		f[i] = inf
		g[i] = -1
	}
	for i := 1; i < 1<<n; i++ {
		if bits.OnesCount(uint(i)) != m {
			continue
		}
		s := map[int]struct{}{}
		mi, mx := 20, 0
		for j, x := range nums {
			if i>>j&1 == 1 {
				if _, ok := s[x]; ok {
					break
				}
				s[x] = struct{}{}
				mi = min(mi, x)
				mx = max(mx, x)
			}
		}
		if len(s) == m {
			g[i] = mx - mi
		}
	}
	f[0] = 0
	for i := 0; i < 1<<n; i++ {
		if f[i] == inf {
			continue
		}
		s := map[int]struct{}{}
		mask := 0
		for j, x := range nums {
			if _, ok := s[x]; !ok && i>>j&1 == 0 {
				s[x] = struct{}{}
				mask |= 1 << j
			}
		}
		if len(s) < m {
			continue
		}
		for j := mask; j > 0; j = (j - 1) & mask {
			if g[j] != -1 {
				f[i|j] = min(f[i|j], f[i]+g[j])
			}
		}
	}
	if f[1<<n-1] == inf {
		return -1
	}
	return f[1<<n-1]
}
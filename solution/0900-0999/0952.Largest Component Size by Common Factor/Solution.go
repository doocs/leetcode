func largestComponentSize(nums []int) int {
	m := slices.Max(nums)
	p := make([]int, m+1)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa != pb {
			p[pa] = pb
		}
	}
	for _, v := range nums {
		i := 2
		for i <= v/i {
			if v%i == 0 {
				union(v, i)
				union(v, v/i)
			}
			i++
		}
	}
	cnt := make([]int, m+1)
	for _, v := range nums {
		t := find(v)
		cnt[t]++
	}
	return slices.Max(cnt)
}
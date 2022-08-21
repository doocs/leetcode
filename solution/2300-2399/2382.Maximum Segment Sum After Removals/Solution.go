func maximumSegmentSum(nums []int, removeQueries []int) []int64 {
	n := len(nums)
	p := make([]int, n)
	s := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	merge := func(a, b int) {
		pa, pb := find(a), find(b)
		p[pa] = pb
		s[pb] += s[pa]
	}
	mx := 0
	ans := make([]int64, n)
	for j := n - 1; j > 0; j-- {
		i := removeQueries[j]
		s[i] = nums[i]
		if i > 0 && s[find(i-1)] > 0 {
			merge(i, i-1)
		}
		if i < n-1 && s[find(i+1)] > 0 {
			merge(i, i+1)
		}
		mx = max(mx, s[find(i)])
		ans[j-1] = int64(mx)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
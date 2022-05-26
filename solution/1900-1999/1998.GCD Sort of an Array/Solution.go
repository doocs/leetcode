var p []int

func gcdSort(nums []int) bool {
	n := 100010
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	mx := 0
	for _, num := range nums {
		mx = max(mx, num)
	}
	f := make([][]int, mx+1)
	for i := 2; i <= mx; i++ {
		if len(f[i]) > 0 {
			continue
		}
		for j := i; j <= mx; j += i {
			f[j] = append(f[j], i)
		}
	}
	for _, i := range nums {
		for _, j := range f[i] {
			p[find(i)] = find(j)
		}
	}
	s := make([]int, len(nums))
	for i, num := range nums {
		s[i] = num
	}
	sort.Ints(s)
	for i, num := range nums {
		if s[i] != num && find(s[i]) != find(num) {
			return false
		}
	}
	return true
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
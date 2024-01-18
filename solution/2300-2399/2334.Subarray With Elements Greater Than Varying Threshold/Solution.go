func validSubarraySize(nums []int, threshold int) int {
	n := len(nums)
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	merge := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size[pb] += size[pa]
	}

	arr := make([][]int, n)
	for i, v := range nums {
		arr[i] = []int{v, i}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][0] > arr[j][0]
	})
	vis := make([]bool, n)
	for _, e := range arr {
		v, i := e[0], e[1]
		if i > 0 && vis[i-1] {
			merge(i, i-1)
		}
		if i < n-1 && vis[i+1] {
			merge(i, i+1)
		}
		if v > threshold/size[find(i)] {
			return size[find(i)]
		}
		vis[i] = true
	}
	return -1
}
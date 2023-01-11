func visibleMountains(peaks [][]int) (ans int) {
	n := len(peaks)
	type pair struct{ l, r int }
	arr := make([]pair, n)
	for _, p := range peaks {
		x, y := p[0], p[1]
		arr = append(arr, pair{x - y, x + y})
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].l < arr[j].l || (arr[i].l == arr[j].l && arr[i].r > arr[j].r) })
	cur := math.MinInt32
	for i, e := range arr {
		l, r := e.l, e.r
		if r <= cur {
			continue
		}
		cur = r
		if !(i < n-1 && l == arr[i+1].l && r == arr[i+1].r) {
			ans++
		}
	}
	return
}
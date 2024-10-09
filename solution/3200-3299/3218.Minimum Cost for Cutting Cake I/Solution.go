func minimumCost(m int, n int, horizontalCut []int, verticalCut []int) (ans int) {
	sort.Sort(sort.Reverse(sort.IntSlice(horizontalCut)))
	sort.Sort(sort.Reverse(sort.IntSlice(verticalCut)))
	i, j := 0, 0
	h, v := 1, 1
	for i < m-1 || j < n-1 {
		if j == n-1 || (i < m-1 && horizontalCut[i] > verticalCut[j]) {
			ans += horizontalCut[i] * v
			h++
			i++
		} else {
			ans += verticalCut[j] * h
			v++
			j++
		}
	}
	return
}
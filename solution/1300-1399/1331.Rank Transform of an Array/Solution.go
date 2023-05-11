func arrayRankTransform(arr []int) (ans []int) {
	t := make([]int, len(arr))
	copy(t, arr)
	sort.Ints(t)
	m := 0
	for i, x := range t {
		if i == 0 || x != t[i-1] {
			t[m] = x
			m++
		}
	}
	t = t[:m]
	for _, x := range arr {
		ans = append(ans, sort.SearchInts(t, x)+1)
	}
	return
}
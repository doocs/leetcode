func minCost(arr []int, brr []int, k int64) int64 {
	calc := func(a, b []int) (ans int64) {
		for i := range a {
			ans += int64(abs(a[i] - b[i]))
		}
		return
	}
	c1 := calc(arr, brr)
	sort.Ints(arr)
	sort.Ints(brr)
	c2 := calc(arr, brr) + k
	return min(c1, c2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func minCost(nums []int, cost []int) int64 {
	n := len(nums)
	type pair struct{ a, b int }
	arr := make([]pair, n)
	mid := 0
	for i, a := range nums {
		b := cost[i]
		mid += b
		arr[i] = pair{a, b}
	}
	mid /= 2
	sort.Slice(arr, func(i, j int) bool { return arr[i].a < arr[j].a })
	s, ans := 0, 0
	for _, e := range arr {
		x, c := e.a, e.b
		s += c
		if s > mid {
			for _, t := range arr {
				ans += abs(t.a-x) * t.b
			}
			break
		}
	}
	return int64(ans)

}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
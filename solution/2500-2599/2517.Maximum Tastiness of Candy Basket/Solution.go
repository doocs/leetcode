func maximumTastiness(price []int, k int) int {
	sort.Ints(price)
	return sort.Search(price[len(price)-1], func(x int) bool {
		cnt, pre := 0, -x
		for _, cur := range price {
			if cur-pre >= x {
				pre = cur
				cnt++
			}
		}
		return cnt < k
	}) - 1
}
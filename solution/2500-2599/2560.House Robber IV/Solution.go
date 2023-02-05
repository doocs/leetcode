func minCapability(nums []int, k int) int {
	return sort.Search(1e9+1, func(x int) bool {
		cnt, j := 0, -2
		for i, v := range nums {
			if v > x || i == j+1 {
				continue
			}
			cnt++
			j = i
		}
		return cnt >= k
	})
}
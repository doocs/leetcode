func minOperations(nums []int, x int) int {
	s := -x
	for _, v := range nums {
		s += v
	}
	vis := map[int]int{0: -1}
	mx, t := -1, 0
	for i, v := range nums {
		t += v
		if _, ok := vis[t]; !ok {
			vis[t] = i
		}
		if j, ok := vis[t-s]; ok {
			mx = max(mx, i-j)
		}
	}
	if mx == -1 {
		return -1
	}
	return len(nums) - mx
}
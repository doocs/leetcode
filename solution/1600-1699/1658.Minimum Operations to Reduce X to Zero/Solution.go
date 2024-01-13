func minOperations(nums []int, x int) int {
	x = -x
	for _, v := range nums {
		x += v
	}
	vis := map[int]int{0: -1}
	ans := 1 << 30
	s, n := 0, len(nums)
	for i, v := range nums {
		s += v
		if _, ok := vis[s]; !ok {
			vis[s] = i
		}
		if j, ok := vis[s-x]; ok {
			ans = min(ans, n-(i-j))
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}
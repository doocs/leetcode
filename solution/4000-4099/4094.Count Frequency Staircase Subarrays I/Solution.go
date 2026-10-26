func countStaircase(nums []int) int {
	n := len(nums)
	ans := 0
	for i := 0; i < n; i++ {
		orders := map[int]int{}
		cnt := map[int]int{}
		ok := 0
		for j := i; j < n; j++ {
			x := nums[j]
			if _, exists := orders[x]; !exists {
				orders[x] = len(orders) + 1
			}
			cnt[x]++
			count := cnt[x]
			order := orders[x]
			if count > order {
				break
			}
			if count == order && func() int { ok++; return ok }() == len(cnt) {
				ans++
			}
		}
	}
	return ans
}

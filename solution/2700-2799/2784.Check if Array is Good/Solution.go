func isGood(nums []int) bool {
	n := len(nums) - 1
	cnt := [201]int{}
	for _, x := range nums {
		cnt[x]++
	}
	cnt[n] -= 2
	for i := 1; i < n; i++ {
		cnt[i]--
	}
	for _, x := range cnt {
		if x != 0 {
			return false
		}
	}
	return true
}
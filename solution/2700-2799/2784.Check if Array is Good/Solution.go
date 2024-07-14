func isGood(nums []int) bool {
	n := len(nums) - 1
	cnt := [201]int{}
	for _, x := range nums {
		cnt[x]++
	}
	if cnt[n] != 2 {
		return false
	}
	for i := 1; i < n; i++ {
		if cnt[i] != 1 {
			return false
		}
	}
	return true
}
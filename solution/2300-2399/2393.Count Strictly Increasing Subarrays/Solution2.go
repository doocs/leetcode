func countSubarrays(nums []int) (ans int64) {
	pre, cnt := 0, 0
	for _, x := range nums {
		if pre < x {
			cnt++
		} else {
			cnt = 1
		}
		ans += int64(cnt)
		pre = x
	}
	return
}
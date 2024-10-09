func countSubarrays(nums []int) int64 {
	ans, cnt := 1, 1
	for i, x := range nums[1:] {
		if nums[i] < x {
			cnt++
		} else {
			cnt = 1
		}
		ans += cnt
	}
	return int64(ans)
}

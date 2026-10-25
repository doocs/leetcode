func countSubarrays(nums []int, minK int, maxK int) (ans int64) {
	l := 0
	for r, x := range nums {
		if x < minK || x > maxK {
			l = r + 1
		} else {
			ans += int64(r - l + 1)
		}
	}
	return
}

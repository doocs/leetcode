func findMaximumScore(nums []int) (ans int64) {
	mx := 0
	for _, x := range nums[:len(nums)-1] {
		mx = max(mx, x)
		ans += int64(mx)
	}
	return
}

func minOperations(nums []int) (ans int) {
	mx := 0
	for _, v := range nums {
		ans += max(0, mx+1-v)
		mx = max(mx+1, v)
	}
	return
}
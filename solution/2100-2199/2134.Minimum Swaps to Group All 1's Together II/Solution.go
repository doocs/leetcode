func minSwaps(nums []int) int {
	k := 0
	for _, x := range nums {
		k += x
	}
	cnt := 0
	for i := 0; i < k; i++ {
		cnt += nums[i]
	}
	mx := cnt
	n := len(nums)
	for i := k; i < n+k; i++ {
		cnt += nums[i%n] - nums[(i-k+n)%n]
		mx = max(mx, cnt)
	}
	return k - mx
}
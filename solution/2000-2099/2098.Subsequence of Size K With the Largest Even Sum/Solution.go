func largestEvenSum(nums []int, k int) int64 {
	sort.Ints(nums)
	ans := 0
	n := len(nums)
	for i := 0; i < k; i++ {
		ans += nums[n-1-i]
	}
	if ans%2 == 0 {
		return int64(ans)
	}
	const inf = 1 << 29
	mx1, mx2 := -inf, -inf
	for _, x := range nums[:n-k] {
		if x%2 == 1 {
			mx1 = x
		} else {
			mx2 = x
		}
	}
	mi1, mi2 := inf, inf
	for i := n - 1; i >= n-k; i-- {
		if nums[i]%2 == 1 {
			mi2 = nums[i]
		} else {
			mi1 = nums[i]
		}
	}
	ans = max(-1, max(ans-mi1+mx1, ans-mi2+mx2))
	if ans%2 != 0 {
		return -1
	}
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
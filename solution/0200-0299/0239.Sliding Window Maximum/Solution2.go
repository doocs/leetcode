func maxSlidingWindow(nums []int, k int) (ans []int) {
	q := []int{}
	for i, v := range nums {
		if len(q) > 0 && i-k+1 > q[0] {
			q = q[1:]
		}
		for len(q) > 0 && nums[q[len(q)-1]] <= v {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		if i >= k-1 {
			ans = append(ans, nums[q[0]])
		}
	}
	return ans
}
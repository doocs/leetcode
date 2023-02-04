func maxSlidingWindow(nums []int, k int) (ans []int) {
	q := []int{}
	for i, x := range nums {
		for len(q) > 0 && i-q[0]+1 > k {
			q = q[1:]
		}
		for len(q) > 0 && nums[q[len(q)-1]] <= x {
			q = q[:len(q)-1]
		}
		q = append(q, i)
		if i >= k-1 {
			ans = append(ans, nums[q[0]])
		}
	}
	return
}
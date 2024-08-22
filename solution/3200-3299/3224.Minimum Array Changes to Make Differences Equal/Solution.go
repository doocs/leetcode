func minChanges(nums []int, k int) int {
	d := make([]int, k+2)
	n := len(nums)
	for i := 0; i < n/2; i++ {
		x, y := nums[i], nums[n-1-i]
		if x > y {
			x, y = y, x
		}
		d[0] += 1
		d[y-x] -= 1
		d[y-x+1] += 1
		d[max(y, k-x)+1] -= 1
		d[max(y, k-x)+1] += 2
	}
	ans, s := n, 0
	for _, x := range d {
		s += x
		ans = min(ans, s)
	}
	return ans
}
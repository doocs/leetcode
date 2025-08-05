func isTrionic(nums []int) bool {
	n := len(nums)
	p := 0
	for p < n-2 && nums[p] < nums[p+1] {
		p++
	}
	if p == 0 {
		return false
	}
	q := p
	for q < n-1 && nums[q] > nums[q+1] {
		q++
	}
	if q == p || q == n-1 {
		return false
	}
	for q < n-1 && nums[q] < nums[q+1] {
		q++
	}
	return q == n-1
}

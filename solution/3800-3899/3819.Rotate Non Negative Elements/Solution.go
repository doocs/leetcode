func rotateElements(nums []int, k int) []int {
	t := make([]int, 0)
	for _, x := range nums {
		if x >= 0 {
			t = append(t, x)
		}
	}
	m := len(t)
	d := make([]int, m)
	for i, x := range t {
		d[((i-k)%m+m)%m] = x
	}
	j := 0
	for i, x := range nums {
		if x >= 0 {
			nums[i] = d[j]
			j++
		}
	}
	return nums
}

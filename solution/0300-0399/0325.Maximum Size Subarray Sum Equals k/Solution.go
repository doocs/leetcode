func maxSubArrayLen(nums []int, k int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		s += x
		if j, ok := d[s-k]; ok && ans < i-j {
			ans = i - j
		}
		if _, ok := d[s]; !ok {
			d[s] = i
		}
	}
	return
}
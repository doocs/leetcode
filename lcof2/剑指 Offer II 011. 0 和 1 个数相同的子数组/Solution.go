func findMaxLength(nums []int) (ans int) {
	d := map[int]int{0: -1}
	s := 0
	for i, x := range nums {
		if x == 1 {
			s++
		} else {
			s--
		}
		if j, ok := d[s]; ok {
			ans = max(ans, i-j)
		} else {
			d[s] = i
		}
	}
	return
}
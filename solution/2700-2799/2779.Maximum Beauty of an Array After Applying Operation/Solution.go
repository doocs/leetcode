func maximumBeauty(nums []int, k int) (ans int) {
	m := 0
	for _, x := range nums {
		if m < x {
			m = x
		}
	}
	m += k*2 + 2
	d := make([]int, m)
	for _, x := range nums {
		d[x]++
		d[x+k*2+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if ans < s {
			ans = s
		}
	}
	return
}
func numberOfPoints(nums [][]int) (ans int) {
	d := [110]int{}
	for _, e := range nums {
		d[e[0]]++
		d[e[1]+1]--
	}
	s := 0
	for _, x := range d {
		s += x
		if s > 0 {
			ans++
		}
	}
	return
}
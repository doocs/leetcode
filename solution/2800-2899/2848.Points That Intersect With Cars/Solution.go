func numberOfPoints(nums [][]int) (ans int) {
	d := [102]int{}
	for _, e := range nums {
		start, end := e[0], e[1]
		d[start]++
		d[end+1]--
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

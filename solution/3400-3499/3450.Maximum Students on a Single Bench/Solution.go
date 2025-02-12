func maxStudentsOnBench(students [][]int) (ans int) {
	d := make(map[int]map[int]struct{})
	for _, e := range students {
		studentId, benchId := e[0], e[1]
		if _, exists := d[benchId]; !exists {
			d[benchId] = make(map[int]struct{})
		}
		d[benchId][studentId] = struct{}{}
	}
	for _, s := range d {
		ans = max(ans, len(s))
	}
	return
}

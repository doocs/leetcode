func arrayChange(nums []int, operations [][]int) []int {
	d := map[int]int{}
	for i, v := range nums {
		d[v] = i
	}
	for _, op := range operations {
		a, b := op[0], op[1]
		idx := d[a]
		delete(d, a)
		d[b] = idx
	}
	ans := make([]int, len(nums))
	for v, i := range d {
		ans[i] = v
	}
	return ans
}
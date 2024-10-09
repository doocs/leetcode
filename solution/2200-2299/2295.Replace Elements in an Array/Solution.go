func arrayChange(nums []int, operations [][]int) []int {
	d := map[int]int{}
	for i, x := range nums {
		d[x] = i
	}
	for _, op := range operations {
		x, y := op[0], op[1]
		nums[d[x]] = y
		d[y] = d[x]
	}
	return nums
}
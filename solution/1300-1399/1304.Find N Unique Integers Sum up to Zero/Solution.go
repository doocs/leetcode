func sumZero(n int) []int {
	presum := 0
	var res []int
	for i := 1; i < n; i++ {
		res = append(res, i)
		presum += i
	}
	res = append(res, -presum)
	return res
}
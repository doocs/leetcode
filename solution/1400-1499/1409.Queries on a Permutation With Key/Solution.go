func processQueries(queries []int, m int) []int {
	nums := make([]int, m)
	for i := 0; i < m; i++ {
		nums[i] = i + 1
	}
	var res []int
	for _, num := range queries {
		idx := -1
		for i := 0; i < m; i++ {
			if nums[i] == num {
				idx = i
				break
			}
		}
		res = append(res, idx)
		nums = append(nums[:idx], nums[idx+1:]...)
		nums = append([]int{num}, nums...)
	}
	return res
}
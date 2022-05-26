func findDisappearedNumbers(nums []int) []int {
	for _, num := range nums {
		idx := abs(num) - 1
		if nums[idx] > 0 {
			nums[idx] *= -1
		}
	}
	var res []int
	for i, num := range nums {
		if num > 0 {
			res = append(res, i+1)
		}
	}
	return res
}

func abs(a int) int {
	if a > 0 {
		return a
	}
	return -a
}
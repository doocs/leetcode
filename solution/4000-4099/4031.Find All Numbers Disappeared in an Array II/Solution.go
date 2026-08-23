func findDisappearedNumbers(nums []int, lower int, upper int) (ans [][]int) {
	sort.Ints(nums)
	prev := lower - 1
	for _, x := range nums {
		if x < lower || x > upper {
			continue
		}
		if x-prev > 1 {
			ans = append(ans, []int{prev + 1, x - 1})
		}
		prev = x
	}
	if prev < upper {
		ans = append(ans, []int{prev + 1, upper})
	}
	return
}

func occurrencesOfElement(nums []int, queries []int, x int) (ans []int) {
	ids := []int{}
	for i, v := range nums {
		if v == x {
			ids = append(ids, i)
		}
	}
	for _, i := range queries {
		if i-1 < len(ids) {
			ans = append(ans, ids[i-1])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
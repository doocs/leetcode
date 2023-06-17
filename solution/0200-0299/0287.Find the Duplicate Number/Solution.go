func findDuplicate(nums []int) int {
	return sort.Search(len(nums), func(x int) bool {
		cnt := 0
		for _, v := range nums {
			if v <= x {
				cnt++
			}
		}
		return cnt > x
	})
}
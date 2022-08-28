func answerQueries(nums []int, queries []int) []int {
	sort.Ints(nums)
	n, m := len(nums), len(queries)
	s := make([]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	ans := make([]int, m)
	for i, v := range queries {
		left, right := 1, len(s)
		for left < right {
			mid := (left + right) >> 1
			if s[mid] > v {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans[i] = left - 1
	}
	return ans
}
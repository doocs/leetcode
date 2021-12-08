func sumEvenAfterQueries(nums []int, queries [][]int) []int {
	s := 0
	for _, num := range nums {
		if num%2 == 0 {
			s += num
		}
	}
	var ans []int
	for _, q := range queries {
		v, i := q[0], q[1]
		old := nums[i]
		nums[i] += v
		if nums[i]%2 == 0 && old%2 == 0 {
			s += v
		} else if nums[i]%2 == 0 && old%2 != 0 {
			s += nums[i]
		} else if old%2 == 0 {
			s -= old
		}
		ans = append(ans, s)
	}
	return ans
}
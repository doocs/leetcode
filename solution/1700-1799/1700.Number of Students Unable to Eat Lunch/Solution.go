func countStudents(students []int, sandwiches []int) int {
	cnt := [2]int{}
	for _, v := range students {
		cnt[v]++
	}
	for _, v := range sandwiches {
		if cnt[v] == 0 {
			return cnt[v^1]
		}
		cnt[v]--
	}
	return 0
}
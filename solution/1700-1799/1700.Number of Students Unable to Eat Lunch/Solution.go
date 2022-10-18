func countStudents(students []int, sandwiches []int) int {
	cnt := [2]int{}
	for _, v := range students {
		cnt[v]++
	}
	for i, v := range sandwiches {
		if cnt[v] == 0 {
			return len(students) - i
		}
		cnt[v]--
	}
	return 0
}
func minMovesToSeat(seats []int, students []int) int {
	sort.Ints(seats)
	sort.Ints(students)
	ans := 0
	for i := range students {
		ans += abs(seats[i] - students[i])
	}
	return ans
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}
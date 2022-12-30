func minMovesToSeat(seats []int, students []int) (ans int) {
	sort.Ints(seats)
	sort.Ints(students)
	for i, a := range seats {
		b := students[i]
		ans += abs(a - b)
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
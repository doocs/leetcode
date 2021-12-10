func isRobotBounded(instructions string) bool {
	direction := make([]int, 4)
	cur := 0
	for _, ins := range instructions {
		if ins == 'L' {
			cur = (cur + 1) % 4
		} else if ins == 'R' {
			cur = (cur + 3) % 4
		} else {
			direction[cur]++
		}
	}
	return cur != 0 || (direction[0] == direction[2] && direction[1] == direction[3])
}
func countCollisions(directions string) int {
	s := strings.TrimRight(strings.TrimLeft(directions, "L"), "R")
	return len(s) - strings.Count(s, "S")
}

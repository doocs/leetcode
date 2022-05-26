func countCollisions(directions string) int {
	d := strings.TrimLeft(directions, "L")
	d = strings.TrimRight(d, "R")
	return len(d) - strings.Count(d, "S")
}
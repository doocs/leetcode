func numOfBurgers(tomatoSlices int, cheeseSlices int) []int {
	k := 4*cheeseSlices - tomatoSlices
	y := k / 2
	x := cheeseSlices - y
	if k%2 != 0 || x < 0 || y < 0 {
		return []int{}
	}
	return []int{x, y}
}
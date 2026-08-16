func nearestDrone(drones [][]int, target []int) int {
	ans := -1
	mn := math.MaxInt32
	tx, ty := target[0], target[1]

	for i, drone := range drones {
		x, y, r := drone[0], drone[1], drone[2]

		d := abs(x-tx) + abs(y-ty)

		if d <= r && mn > d {
			ans = i
			mn = d
		}
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

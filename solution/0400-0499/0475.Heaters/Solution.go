func findRadius(houses []int, heaters []int) int {
	sort.Ints(houses)
	sort.Ints(heaters)
	m, n := len(houses), len(heaters)

	check := func(r int) bool {
		var i, j int
		for i < m {
			if j >= n {
				return false
			}
			mi, mx := heaters[j]-r, heaters[j]+r
			if houses[i] < mi {
				return false
			}
			if houses[i] > mx {
				j++
			} else {
				i++
			}
		}
		return true
	}
	left, right := 0, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		if check(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
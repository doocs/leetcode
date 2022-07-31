func maximumGroups(grades []int) int {
	sort.Ints(grades)
	ans := 1
	prev := []int{1, grades[0]}
	curr := []int{0, 0}
	for _, v := range grades[1:] {
		curr[0]++
		curr[1] += v
		if prev[0] < curr[0] && prev[1] < curr[1] {
			prev = curr
			curr = []int{0, 0}
			ans++
		}
	}
	return ans
}
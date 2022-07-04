func maxNumberOfApples(weight []int) int {
	sort.Ints(weight)
	ans, t := 0, 0
	for _, v := range weight {
		if t+v > 5000 {
			break
		}
		t += v
		ans++
	}
	return ans
}
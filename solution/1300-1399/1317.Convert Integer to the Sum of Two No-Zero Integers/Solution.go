func getNoZeroIntegers(n int) []int {
	for a := 1; ; a++ {
		b := n - a
		if !strings.Contains(strconv.Itoa(a)+strconv.Itoa(b), "0") {
			return []int{a, b}
		}
	}
}
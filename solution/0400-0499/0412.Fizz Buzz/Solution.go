func fizzBuzz(n int) []string {
	ans := make([]string, 0, n)
	for i := 1; i < n+1; i++ {
		switch {
		case i%15 == 0:
			ans = append(ans, "FizzBuzz")
		case i%3 == 0:
			ans = append(ans, "Fizz")
		case i%5 == 0:
			ans = append(ans, "Buzz")
		default:
			ans = append(ans, strconv.Itoa(i))
		}
	}
	return ans
}

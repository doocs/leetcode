func fizzBuzz(n int) []string {
	var ans []string
	for i := 1; i <= n; i++ {
		s := &strings.Builder{}
		if i%3 == 0 {
			s.WriteString("Fizz")
		}
		if i%5 == 0 {
			s.WriteString("Buzz")
		}
		if s.Len() == 0 {
			s.WriteString(strconv.Itoa(i))
		}
		ans = append(ans, s.String())
	}
	return ans
}
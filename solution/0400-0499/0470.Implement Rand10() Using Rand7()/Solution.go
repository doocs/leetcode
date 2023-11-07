func rand10() int {
	for {
		i := rand7() - 1
		j := rand7()
		x := i*7 + j
		if x <= 40 {
			return x%10 + 1
		}
	}
}
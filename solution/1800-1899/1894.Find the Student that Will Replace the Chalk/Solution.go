func chalkReplacer(chalk []int, k int) int {
	s := 0
	for _, x := range chalk {
		s += x
	}
	k %= s
	for i := 0; ; i++ {
		if k < chalk[i] {
			return i
		}
		k -= chalk[i]
	}
}
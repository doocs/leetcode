func subarrayBitwiseORs(arr []int) int {
	s := map[int]bool{}
	prev := 0
	for i, v := range arr {
		prev |= v
		curr := 0
		for j := i; j >= 0; j-- {
			curr |= arr[j]
			s[curr] = true
			if curr == prev {
				break
			}
		}
	}
	return len(s)
}
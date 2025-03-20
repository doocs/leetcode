func countTriplets(arr []int) (ans int) {
	for i, x := range arr {
		s := x
		for k := i + 1; k < len(arr); k++ {
			s ^= arr[k]
			if s == 0 {
				ans += k - i
			}
		}
	}
	return
}

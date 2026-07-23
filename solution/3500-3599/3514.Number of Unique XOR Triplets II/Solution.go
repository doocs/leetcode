func uniqueXorTriplets(nums []int) int {
	mx := slices.Max(nums) << 1

	st := make([]bool, mx)
	for _, a := range nums {
		for _, b := range nums {
			st[a^b] = true
		}
	}

	s := make([]int, mx)
	for ab := 0; ab < mx; ab++ {
		if st[ab] {
			for _, c := range nums {
				s[ab^c] = 1
			}
		}
	}

	ans := 0
	for _, v := range s {
		ans += v
	}
	return ans
}

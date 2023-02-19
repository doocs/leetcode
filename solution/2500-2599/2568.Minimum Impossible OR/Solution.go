func minImpossibleOR(nums []int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for i := 0; ; i++ {
		if !s[1<<i] {
			return 1 << i
		}
	}
}
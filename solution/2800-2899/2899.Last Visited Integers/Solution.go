func lastVisitedIntegers(nums []int) []int {
	seen := []int{}
	ans := []int{}
	k := 0

	for _, x := range nums {
		if x == -1 {
			k++
			if k > len(seen) {
				ans = append(ans, -1)
			} else {
				ans = append(ans, seen[len(seen)-k])
			}
		} else {
			k = 0
			seen = append(seen, x)
		}
	}

	return ans
}

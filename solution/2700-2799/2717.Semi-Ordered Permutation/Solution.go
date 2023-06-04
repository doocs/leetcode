func semiOrderedPermutation(nums []int) int {
	n := len(nums)
	var i, j int
	for k, x := range nums {
		if x == 1 {
			i = k
		}
		if x == n {
			j = k
		}
	}
	k := 1
	if i > j {
		k = 2
	}
	return i + n - j - k
}
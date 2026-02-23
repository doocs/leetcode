func scoreDifference(nums []int) int {
	ans := 0
	k := 1
	for i, x := range nums {
		if x%2 != 0 {
			k = -k
		}
		if i%6 == 5 {
			k = -k
		}
		ans += k * x
	}
	return ans
}

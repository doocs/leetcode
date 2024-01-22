func minimumArrayLength(nums []int) int {
	mi := slices.Min(nums)
	cnt := 0
	for _, x := range nums {
		if x%mi != 0 {
			return 1
		}
		if x == mi {
			cnt++
		}
	}
	return (cnt + 1) / 2
}
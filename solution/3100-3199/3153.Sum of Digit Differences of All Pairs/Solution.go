func sumDigitDifferences(nums []int) (ans int64) {
	n := len(nums)
	m := int(math.Floor(math.Log10(float64(nums[0])))) + 1
	for k := 0; k < m; k++ {
		cnt := [10]int{}
		for i, x := range nums {
			cnt[x%10]++
			nums[i] /= 10
		}
		for _, v := range cnt {
			ans += int64(v) * int64(n-v)
		}
	}
	ans /= 2
	return
}
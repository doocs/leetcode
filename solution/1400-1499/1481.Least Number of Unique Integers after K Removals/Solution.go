func findLeastNumOfUniqueInts(arr []int, k int) int {
	cnt := map[int]int{}
	for _, x := range arr {
		cnt[x]++
	}
	nums := make([]int, 0, len(cnt))
	for _, v := range cnt {
		nums = append(nums, v)
	}
	sort.Ints(nums)
	for i, v := range nums {
		k -= v
		if k < 0 {
			return len(nums) - i
		}
	}
	return 0
}
func countCompleteSubarrays(nums []int) (ans int) {
	d := map[int]int{}
	for _, x := range nums {
		d[x] = 1
	}
	cnt := len(d)
	i, n := 0, len(nums)
	d = map[int]int{}
	for j, x := range nums {
		d[x]++
		for len(d) == cnt {
			ans += n - j
			d[nums[i]]--
			if d[nums[i]] == 0 {
				delete(d, nums[i])
			}
			i++
		}
	}
	return
}
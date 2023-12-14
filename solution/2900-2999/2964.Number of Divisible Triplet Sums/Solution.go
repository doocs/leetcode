func divisibleTripletCount(nums []int, d int) (ans int) {
	n := len(nums)
	cnt := map[int]int{}
	for j := 0; j < n; j++ {
		for k := j + 1; k < n; k++ {
			x := (d - (nums[j]+nums[k])%d) % d
			ans += cnt[x]
		}
		cnt[nums[j]%d]++
	}
	return
}
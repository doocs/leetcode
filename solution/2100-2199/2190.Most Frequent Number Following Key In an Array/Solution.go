func mostFrequent(nums []int, key int) (ans int) {
	cnt := [1001]int{}
	mx := 0
	for i, x := range nums[1:] {
		if nums[i] == key {
			cnt[x]++
			if mx < cnt[x] {
				mx = cnt[x]
				ans = x
			}
		}
	}
	return
}
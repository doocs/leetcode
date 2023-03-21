func beautifulSubarrays(nums []int) (ans int64) {
	cnt := map[int]int{0: 1}
	mask := 0
	for _, x := range nums {
		mask ^= x
		ans += int64(cnt[mask])
		cnt[mask]++
	}
	return
}
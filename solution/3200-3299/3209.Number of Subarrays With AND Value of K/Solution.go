func countSubarrays(nums []int, k int) (ans int64) {
	pre := map[int]int{}
	for _, x := range nums {
		cur := map[int]int{}
		for y, v := range pre {
			cur[x&y] += v
		}
		cur[x]++
		ans += int64(cur[k])
		pre = cur
	}
	return
}
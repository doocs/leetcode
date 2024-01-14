func maxFrequencyElements(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	mx := -1
	for _, x := range cnt {
		if mx < x {
			mx, ans = x, x
		} else if mx == x {
			ans += x
		}
	}
	return
}
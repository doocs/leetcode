func maximumNumberOfOnes(width int, height int, sideLength int, maxOnes int) int {
	x := sideLength
	cnt := make([]int, x*x)
	for i := 0; i < width; i++ {
		for j := 0; j < height; j++ {
			k := (i%x)*x + (j % x)
			cnt[k]++
		}
	}
	sort.Ints(cnt)
	ans := 0
	for i := range cnt[:maxOnes] {
		ans += cnt[len(cnt)-i-1]
	}
	return ans
}
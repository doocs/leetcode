func largestOverlap(img1 [][]int, img2 [][]int) (ans int) {
	type pair struct{ x, y int }
	cnt := map[pair]int{}
	for i, row1 := range img1 {
		for j, x1 := range row1 {
			if x1 == 1 {
				for h, row2 := range img2 {
					for k, x2 := range row2 {
						if x2 == 1 {
							t := pair{i - h, j - k}
							cnt[t]++
							ans = max(ans, cnt[t])
						}
					}
				}
			}
		}
	}
	return
}
func findPeaks(mountain []int) (ans []int) {
	for i := 1; i < len(mountain)-1; i++ {
		if mountain[i-1] < mountain[i] && mountain[i+1] < mountain[i] {
			ans = append(ans, i)
		}
	}
	return
}
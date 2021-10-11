func trimMean(arr []int) float64 {
	sort.Ints(arr)
	n := len(arr)
	sum := 0.0
	for i := n / 20; i < n-n/20; i++ {
		sum += float64(arr[i])
	}
	return sum / (float64(n) * 0.9)
}
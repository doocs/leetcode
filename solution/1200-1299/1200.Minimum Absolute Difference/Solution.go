func minimumAbsDifference(arr []int) [][]int {
	sort.Ints(arr)
	mi := math.MaxInt32
	var ans [][]int
	for i, a := range arr[:len(arr)-1] {
		b := arr[i+1]
		d := b - a
		if d < mi {
			mi = d
			ans = [][]int{[]int{a, b}}
		} else if d == mi {
			ans = append(ans, []int{a, b})
		}
	}
	return ans
}
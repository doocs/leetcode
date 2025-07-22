func maxConsecutive(bottom int, top int, special []int) int {
	sort.Ints(special)
	ans := max(special[0]-bottom, top-special[len(special)-1])
	for i, x := range special[1:] {
		ans = max(ans, x-special[i]-1)
	}
	return ans
}

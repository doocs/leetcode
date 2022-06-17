func maximumEvenSplit(finalSum int64) []int64 {
	ans := []int64{}
	if finalSum%2 == 1 {
		return ans
	}
	for i := int64(2); i <= finalSum; i += 2 {
		ans = append(ans, i)
		finalSum -= i
	}
	ans[len(ans)-1] += finalSum
	return ans
}
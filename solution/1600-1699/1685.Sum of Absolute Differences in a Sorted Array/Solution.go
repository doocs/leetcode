func getSumAbsoluteDifferences(nums []int) (ans []int) {
	var s, t int
	for _, x := range nums {
		s += x
	}
	for i, x := range nums {
		v := x*i - t + s - t - x*(len(nums)-i)
		ans = append(ans, v)
		t += x
	}
	return
}
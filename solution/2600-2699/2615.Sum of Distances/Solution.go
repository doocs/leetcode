func distance(nums []int) []int64 {
	n := len(nums)
	ans := make([]int64, n)
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	for _, idx := range d {
		m := len(idx)
		left, right := 0, -m*idx[0]
		for _, i := range idx {
			right += i
		}
		for i := range idx {
			ans[idx[i]] = int64(left + right)
			if i+1 < m {
				left += (idx[i+1] - idx[i]) * (i + 1)
				right -= (idx[i+1] - idx[i]) * (m - i - 1)
			}
		}
	}
	return ans
}
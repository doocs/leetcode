func smallestRange(nums [][]int) []int {
	t := [][]int{}
	for i, x := range nums {
		for _, v := range x {
			t = append(t, []int{v, i})
		}
	}
	sort.Slice(t, func(i, j int) bool { return t[i][0] < t[j][0] })
	ans := []int{-1000000, 1000000}
	j := 0
	cnt := map[int]int{}
	for _, x := range t {
		b, v := x[0], x[1]
		cnt[v]++
		for len(cnt) == len(nums) {
			a, w := t[j][0], t[j][1]
			x := b - a - (ans[1] - ans[0])
			if x < 0 || (x == 0 && a < ans[0]) {
				ans[0], ans[1] = a, b
			}
			cnt[w]--
			if cnt[w] == 0 {
				delete(cnt, w)
			}
			j++
		}
	}
	return ans
}
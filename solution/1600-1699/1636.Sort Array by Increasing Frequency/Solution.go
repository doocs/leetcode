func frequencySort(nums []int) []int {
	cnt := make([]int, 201)
	for _, v := range nums {
		cnt[v+100]++
	}
	var t [][]int
	for i, v := range cnt {
		if v > 0 {
			t = append(t, []int{v, i})
		}
	}
	sort.Slice(t, func(i, j int) bool {
		if t[i][0] == t[j][0] {
			return t[i][1] > t[j][1]
		}
		return t[i][0] < t[j][0]
	})
	var ans []int
	for _, e := range t {
		for i := 0; i < e[0]; i++ {
			ans = append(ans, e[1]-100)
		}
	}
	return ans
}
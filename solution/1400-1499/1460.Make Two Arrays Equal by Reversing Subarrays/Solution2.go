func canBeEqual(target []int, arr []int) bool {
	cnt1 := make([]int, 1001)
	cnt2 := make([]int, 1001)
	for _, v := range target {
		cnt1[v]++
	}
	for _, v := range arr {
		cnt2[v]++
	}
	for i, v := range cnt1 {
		if v != cnt2[i] {
			return false
		}
	}
	return true
}
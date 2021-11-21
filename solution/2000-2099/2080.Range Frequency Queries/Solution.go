type RangeFreqQuery struct {
	mp map[int][]int
}

func Constructor(arr []int) RangeFreqQuery {
	mp := make(map[int][]int)
	for i, v := range arr {
		mp[v] = append(mp[v], i)
	}
	return RangeFreqQuery{mp}
}

func (this *RangeFreqQuery) Query(left int, right int, value int) int {
	arr := this.mp[value]
	l := sort.SearchInts(arr, left)
	r := sort.SearchInts(arr, right+1)
	return r - l
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * obj := Constructor(arr);
 * param_1 := obj.Query(left,right,value);
 */
type FindSumPairs struct {
	nums1 []int
	nums2 []int
	cnt   map[int]int
}

func Constructor(nums1 []int, nums2 []int) FindSumPairs {
	cnt := map[int]int{}
	for _, v := range nums2 {
		cnt[v]++
	}
	return FindSumPairs{nums1, nums2, cnt}
}

func (this *FindSumPairs) Add(index int, val int) {
	old := this.nums2[index]
	this.cnt[old]--
	this.cnt[old+val]++
	this.nums2[index] += val
}

func (this *FindSumPairs) Count(tot int) (ans int) {
	for _, v := range this.nums1 {
		ans += this.cnt[tot-v]
	}
	return
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * obj := Constructor(nums1, nums2);
 * obj.Add(index,val);
 * param_2 := obj.Count(tot);
 */
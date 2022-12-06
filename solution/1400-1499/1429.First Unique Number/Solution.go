type FirstUnique struct {
	cnt map[int]int
	q   []int
}

func Constructor(nums []int) FirstUnique {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	return FirstUnique{cnt, nums}
}

func (this *FirstUnique) ShowFirstUnique() int {
	for len(this.q) > 0 && this.cnt[this.q[0]] != 1 {
		this.q = this.q[1:]
	}
	if len(this.q) > 0 {
		return this.q[0]
	}
	return -1
}

func (this *FirstUnique) Add(value int) {
	this.cnt[value]++
	this.q = append(this.q, value)
}

/**
 * Your FirstUnique object will be instantiated and called as such:
 * obj := Constructor(nums);
 * param_1 := obj.ShowFirstUnique();
 * obj.Add(value);
 */
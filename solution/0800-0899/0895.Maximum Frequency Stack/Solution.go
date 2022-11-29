type FreqStack struct {
	cnt map[int]int
	d   map[int][]int
	mx  int
}

func Constructor() FreqStack {
	return FreqStack{map[int]int{}, map[int][]int{}, 0}
}

func (this *FreqStack) Push(val int) {
	this.cnt[val]++
	this.d[this.cnt[val]] = append(this.d[this.cnt[val]], val)
	this.mx = max(this.mx, this.cnt[val])
}

func (this *FreqStack) Pop() int {
	val := this.d[this.mx][len(this.d[this.mx])-1]
	this.d[this.mx] = this.d[this.mx][:len(this.d[this.mx])-1]
	this.cnt[val]--
	if len(this.d[this.mx]) == 0 {
		this.mx--
	}
	return val
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * param_2 := obj.Pop();
 */
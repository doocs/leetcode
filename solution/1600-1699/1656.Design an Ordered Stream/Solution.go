type OrderedStream struct {
	data []string
	ptr  int
}

func Constructor(n int) OrderedStream {
	data := make([]string, n)
	return OrderedStream{data, 0}
}

func (this *OrderedStream) Insert(idKey int, value string) []string {
	this.data[idKey-1] = value
	var ans []string
	for this.ptr < len(this.data) && this.data[this.ptr] != "" {
		ans = append(ans, this.data[this.ptr])
		this.ptr++
	}
	return ans
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Insert(idKey,value);
 */
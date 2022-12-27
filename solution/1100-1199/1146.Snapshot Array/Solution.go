type SnapshotArray struct {
	idx int
	arr [][]pair
}

func Constructor(length int) SnapshotArray {
	return SnapshotArray{0, make([][]pair, length)}
}

func (this *SnapshotArray) Set(index int, val int) {
	this.arr[index] = append(this.arr[index], pair{this.idx, val})
}

func (this *SnapshotArray) Snap() int {
	this.idx++
	return this.idx - 1
}

func (this *SnapshotArray) Get(index int, snap_id int) int {
	vals := this.arr[index]
	i := sort.Search(len(vals), func(i int) bool { return vals[i].i > snap_id })
	if i == 0 {
		return 0
	}
	return vals[i-1].v
}

type pair struct{ i, v int }

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * obj := Constructor(length);
 * obj.Set(index,val);
 * param_2 := obj.Snap();
 * param_3 := obj.Get(index,snap_id);
 */
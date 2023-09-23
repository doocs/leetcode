type LockingTree struct {
	locked   []int
	parent   []int
	children [][]int
}

func Constructor(parent []int) LockingTree {
	n := len(parent)
	locked := make([]int, n)
	for i := range locked {
		locked[i] = -1
	}
	children := make([][]int, n)
	for i := 1; i < n; i++ {
		children[parent[i]] = append(children[parent[i]], i)
	}
	return LockingTree{locked, parent, children}
}

func (this *LockingTree) Lock(num int, user int) bool {
	if this.locked[num] == -1 {
		this.locked[num] = user
		return true
	}
	return false
}

func (this *LockingTree) Unlock(num int, user int) bool {
	if this.locked[num] == user {
		this.locked[num] = -1
		return true
	}
	return false
}

func (this *LockingTree) Upgrade(num int, user int) bool {
	x := num
	for ; x != -1; x = this.parent[x] {
		if this.locked[x] != -1 {
			return false
		}
	}
	find := false
	var dfs func(int)
	dfs = func(x int) {
		for _, y := range this.children[x] {
			if this.locked[y] != -1 {
				find = true
				this.locked[y] = -1
			}
			dfs(y)
		}
	}
	dfs(num)
	if !find {
		return false
	}
	this.locked[num] = user
	return true
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * obj := Constructor(parent);
 * param_1 := obj.Lock(num,user);
 * param_2 := obj.Unlock(num,user);
 * param_3 := obj.Upgrade(num,user);
 */
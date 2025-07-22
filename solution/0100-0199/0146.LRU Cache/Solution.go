type Node struct {
	key, val   int
	prev, next *Node
}

type LRUCache struct {
	size, capacity int
	head, tail     *Node
	cache          map[int]*Node
}

func Constructor(capacity int) LRUCache {
	head := &Node{}
	tail := &Node{}
	head.next = tail
	tail.prev = head
	return LRUCache{
		capacity: capacity,
		head:     head,
		tail:     tail,
		cache:    make(map[int]*Node),
	}
}

func (this *LRUCache) Get(key int) int {
	if node, exists := this.cache[key]; exists {
		this.removeNode(node)
		this.addToHead(node)
		return node.val
	}
	return -1
}

func (this *LRUCache) Put(key int, value int) {
	if node, exists := this.cache[key]; exists {
		this.removeNode(node)
		node.val = value
		this.addToHead(node)
	} else {
		node := &Node{key: key, val: value}
		this.cache[key] = node
		this.addToHead(node)
		if this.size++; this.size > this.capacity {
			node = this.tail.prev
			delete(this.cache, node.key)
			this.removeNode(node)
			this.size--
		}
	}
}

func (this *LRUCache) removeNode(node *Node) {
	node.prev.next = node.next
	node.next.prev = node.prev
}

func (this *LRUCache) addToHead(node *Node) {
	node.next = this.head.next
	node.prev = this.head
	this.head.next = node
	node.next.prev = node
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */

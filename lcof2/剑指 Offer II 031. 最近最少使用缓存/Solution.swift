class Node {
    var key: Int
    var val: Int
    var prev: Node?
    var next: Node?

    init() {
        self.key = 0
        self.val = 0
    }

    init(_ key: Int, _ val: Int) {
        self.key = key
        self.val = val
    }
}

class LRUCache {
    private var cache = [Int: Node]()
    private let head: Node
    private let tail: Node
    private let capacity: Int
    private var size: Int

    init(_ capacity: Int) {
        self.capacity = capacity
        self.size = 0
        self.head = Node()
        self.tail = Node()
        head.next = tail
        tail.prev = head
    }

    func get(_ key: Int) -> Int {
        guard let node = cache[key] else {
            return -1
        }
        moveToHead(node)
        return node.val
    }

    func put(_ key: Int, _ value: Int) {
        if let node = cache[key] {
            node.val = value
            moveToHead(node)
        } else {
            let newNode = Node(key, value)
            cache[key] = newNode
            addToHead(newNode)
            size += 1
            if size > capacity {
                let tail = removeTail()
                cache.removeValue(forKey: tail.key)
                size -= 1
            }
        }
    }

    private func moveToHead(_ node: Node) {
        removeNode(node)
        addToHead(node)
    }

    private func removeNode(_ node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    private func addToHead(_ node: Node) {
        node.next = head.next
        node.prev = head
        head.next?.prev = node
        head.next = node
    }

    private func removeTail() -> Node {
        let node = tail.prev!
        removeNode(node)
        return node
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * let obj = LRUCache(capacity)
 * let ret_1: Int = obj.get(key)
 * obj.put(key, value)
 */

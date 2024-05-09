class Node {
    var key: Int
    var val: Int
    var prev: Node?
    var next: Node?

    init(_ key: Int = 0, _ val: Int = 0) {
        self.key = key
        self.val = val
    }
}

class LRUCache {
    private var cache: [Int: Node] = [:]
    private let head: Node = Node()
    private let tail: Node = Node()
    private var capacity: Int
    private var size: Int = 0

    init(_ capacity: Int) {
        self.capacity = capacity
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
                if let tailNode = removeTail() {
                    cache.removeValue(forKey: tailNode.key)
                    size -= 1
                }
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
        node.prev = head
        node.next = head.next
        head.next?.prev = node
        head.next = node
    }

    private func removeTail() -> Node? {
        guard let res = tail.prev, res !== head else {
            return nil
        }
        removeNode(res)
        return res
    }
}
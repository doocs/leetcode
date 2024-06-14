class Solution {
    func containsNearbyAlmostDuplicate(_ nums: [Int], _ k: Int, _ t: Int) -> Bool {
        guard nums.count > 1, k > 0, t >= 0 else { return false }

        var ts = TreeSet<Int64>()
        for i in 0..<nums.count {
            let num = Int64(nums[i])
            if let x = ts.ceiling(num - Int64(t)), abs(x - num) <= Int64(t) {
                return true
            }
            ts.insert(num)
            if i >= k {
                ts.remove(Int64(nums[i - k]))
            }
        }
        return false
    }
}

class AVLTreeNode<T: Comparable> {
    var value: T
    var height: Int
    var left: AVLTreeNode?
    var right: AVLTreeNode?
    
    init(value: T) {
        self.value = value
        self.height = 1
    }
}

class TreeSet<T: Comparable> {
    private var root: AVLTreeNode<T>?

    func insert(_ value: T) {
        root = insert(root, value)
    }

    func remove(_ value: T) {
        root = remove(root, value)
    }

    func ceiling(_ value: T) -> T? {
        return ceiling(root, value)
    }

    private func insert(_ node: AVLTreeNode<T>?, _ value: T) -> AVLTreeNode<T> {
        guard let node = node else {
            return AVLTreeNode(value: value)
        }
        
        if value < node.value {
            node.left = insert(node.left, value)
        } else if value > node.value {
            node.right = insert(node.right, value)
        } else {
            return node
        }
        
        return balance(node)
    }
    
    private func remove(_ node: AVLTreeNode<T>?, _ value: T) -> AVLTreeNode<T>? {
        guard let node = node else {
            return nil
        }
        
        if value < node.value {
            node.left = remove(node.left, value)
        } else if value > node.value {
            node.right = remove(node.right, value)
        } else {
            if node.left == nil {
                return node.right
            } else if node.right == nil {
                return node.left
            } else {
                if let minLargerNode = minNode(node.right) {
                    node.value = minLargerNode.value
                    node.right = remove(node.right, minLargerNode.value)
                }
            }
        }
        
        return balance(node)
    }
    
    private func ceiling(_ node: AVLTreeNode<T>?, _ value: T) -> T? {
        guard let node = node else {
            return nil
        }
        
        if node.value == value {
            return node.value
        } else if node.value < value {
            return ceiling(node.right, value)
        } else {
            return ceiling(node.left, value) ?? node.value
        }
    }
    
    private func height(_ node: AVLTreeNode<T>?) -> Int {
        return node?.height ?? 0
    }
    
    private func balanceFactor(_ node: AVLTreeNode<T>) -> Int {
        return height(node.left) - height(node.right)
    }
    
    private func updateHeight(_ node: AVLTreeNode<T>) {
        node.height = 1 + max(height(node.left), height(node.right))
    }
    
    private func rotateRight(_ y: AVLTreeNode<T>) -> AVLTreeNode<T> {
        let x = y.left!
        let T2 = x.right
        
        x.right = y
        y.left = T2
        
        updateHeight(y)
        updateHeight(x)
        
        return x
    }
    
    private func rotateLeft(_ x: AVLTreeNode<T>) -> AVLTreeNode<T> {
        let y = x.right!
        let T2 = y.left
        
        y.left = x
        x.right = T2
        
        updateHeight(x)
        updateHeight(y)
        
        return y
    }
    
    private func balance(_ node: AVLTreeNode<T>) -> AVLTreeNode<T> {
        updateHeight(node)
        
        let balance = balanceFactor(node)
        
        if balance > 1 {
            if balanceFactor(node.left!) < 0 {
                node.left = rotateLeft(node.left!)
            }
            return rotateRight(node)
        }
        
        if balance < -1 {
            if balanceFactor(node.right!) > 0 {
                node.right = rotateRight(node.right!)
            }
            return rotateLeft(node)
        }
        
        return node
    }
    
    private func minNode(_ node: AVLTreeNode<T>?) -> AVLTreeNode<T>? {
        var current = node
        while current?.left != nil {
            current = current?.left
        }
        return current
    }
}

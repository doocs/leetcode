class RandomizedSet {
    private var m: [Int: Int]
    private var a: [Int]
    
    init() {
        self.m = [Int: Int]()
        self.a = [Int]()
    }
    
    func insert(_ val: Int) -> Bool {
        if m[val] != nil {
            return false
        }
        m[val] = a.count
        a.append(val)
        return true
    }
    
    func remove(_ val: Int) -> Bool {
        if let idx = m[val] {
            let last = a.count - 1
            if idx != last {
                a.swapAt(idx, last)
                m[a[idx]] = idx
            }
            a.removeLast()
            m.removeValue(forKey: val)
            return true
        }
        return false
    }
    
    func getRandom() -> Int {
        return a[Int.random(in: 0..<a.count)]
    }
}

/* let obj = RandomizedSet()
* let param_1 = obj.insert(val)
* let param_2 = obj.insert(val)
* let param_3 = obj.remove(val)
* let param_4 = obj.getRandom()
*/
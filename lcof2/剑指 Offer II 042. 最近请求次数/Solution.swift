class RecentCounter {
    private var q: [Int]
    
    init() {
        q = []
    }
    
    func ping(_ t: Int) -> Int {
        q.append(t)
        while q.first! < t - 3000 {
            q.removeFirst()
        }
        return q.count
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * let obj = RecentCounter()
 * let param_1 = obj.ping(t)
 */
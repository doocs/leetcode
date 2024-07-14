class MapSum {
    private var data: [String: Int]
    private var t: [String: Int]

    init() {
        data = [String: Int]()
        t = [String: Int]()
    }

    func insert(_ key: String, _ val: Int) {
        let old = t[key] ?? 0
        t[key] = val
        for i in 1...key.count {
            let endIndex = key.index(key.startIndex, offsetBy: i)
            let k = String(key[key.startIndex..<endIndex])
            data[k, default: 0] += (val - old)
        }
    }

    func sum(_ prefix: String) -> Int {
        return data[prefix] ?? 0
    }
}
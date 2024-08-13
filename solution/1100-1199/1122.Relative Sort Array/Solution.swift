class Solution {
    func relativeSortArray(_ arr1: [Int], _ arr2: [Int]) -> [Int] {
        var pos = [Int: Int]()
        for (i, x) in arr2.enumerated() {
            pos[x] = i
        }
        var arr = [(Int, Int)]()
        for x in arr1 {
            let j = pos[x] ?? arr2.count
            arr.append((j, x))
        }
        arr.sort { $0.0 < $1.0 || ($0.0 == $1.0 && $0.1 < $1.1) }
        return arr.map { $0.1 }
    }
}

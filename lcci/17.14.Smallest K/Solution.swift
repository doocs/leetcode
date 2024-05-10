class Solution {
    func smallestK(_ arr: [Int], _ k: Int) -> [Int] {
        guard k > 0 else { return [] }
        let sortedArray = arr.sorted()
        return Array(sortedArray.prefix(k))
    }
}
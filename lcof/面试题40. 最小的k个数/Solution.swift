class Solution {
    func getLeastNumbers(_ arr: [Int], _ k: Int) -> [Int] {
        let sortedArr = arr.sorted()
        return Array(sortedArr.prefix(k))
    }
}
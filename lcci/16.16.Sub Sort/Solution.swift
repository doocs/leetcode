class Solution {
    func subSort(_ array: [Int]) -> [Int] {
        let n = array.count
        var mi = Int.max, mx = Int.min
        var left = -1, right = -1

        for i in 0..<n {
            if array[i] < mx {
                right = i
            } else {
                mx = array[i]
            }
        }

        for i in stride(from: n - 1, through: 0, by: -1) {
            if array[i] > mi {
                left = i
            } else {
                mi = array[i]
            }
        }

        return [left, right]
    }
}
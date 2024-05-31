class Solution {
    func twoSum(_ numbers: [Int], _ target: Int) -> [Int] {
        let n = numbers.count
        for i in 0..<n {
            let x = target - numbers[i]
            var l = i + 1
            var r = n - 1
            while l < r {
                let mid = (l + r) / 2
                if numbers[mid] >= x {
                    r = mid
                } else {
                    l = mid + 1
                }
            }
            if l < n && numbers[l] == x {
                return [i, l]
            }
        }
        return []
    }
}

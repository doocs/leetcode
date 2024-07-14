class Solution {
    func peakIndexInMountainArray(_ arr: [Int]) -> Int {
        var left = 1
        var right = arr.count - 2
        while left < right {
            let mid = (left + right) / 2
            if arr[mid] > arr[mid + 1] {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}


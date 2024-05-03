class Solution {
    func searchMatrix(_ matrix: [[Int]], _ target: Int) -> Bool {
        for row in matrix {
            if binarySearch(row, target) {
                return true
            }
        }
        return false
    }
    
    private func binarySearch(_ array: [Int], _ target: Int) -> Bool {
        var left = 0
        var right = array.count - 1
        
        while left <= right {
            let mid = left + (right - left) / 2
            if array[mid] == target {
                return true
            } else if array[mid] < target {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        
        return false
    }
}
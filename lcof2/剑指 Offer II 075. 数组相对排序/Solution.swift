class Solution {
    func relativeSortArray(_ arr1: [Int], _ arr2: [Int]) -> [Int] {
        var frequency = [Int](repeating: 0, count: 1001)
        var result = [Int]()
        
        for num in arr1 {
            frequency[num] += 1
        }
        
        for num in arr2 {
            while frequency[num] > 0 {
                result.append(num)
                frequency[num] -= 1
            }
        }
        
        for num in 0..<frequency.count {
            while frequency[num] > 0 {
                result.append(num)
                frequency[num] -= 1
            }
        }
        
        return result
    }
}

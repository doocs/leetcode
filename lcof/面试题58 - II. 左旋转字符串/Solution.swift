class Solution {
    func reverseLeftWords(_ s: String, _ n: Int) -> String {
        let leftIndex = s.index(s.startIndex, offsetBy: n)
        let rightPart = s[leftIndex..<s.endIndex]
        let leftPart = s[s.startIndex..<leftIndex]
        return String(rightPart) + String(leftPart)
    }
}
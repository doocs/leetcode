class Solution {
    func groupAnagrams(_ strs: [String]) -> [[String]] {
        var d = [String: [String]]()
        
        for s in strs {
            let sortedStr = String(s.sorted())
            if d[sortedStr] == nil {
                d[sortedStr] = [String]()
            }
            d[sortedStr]!.append(s)
        }
        
        return Array(d.values)
    }
}
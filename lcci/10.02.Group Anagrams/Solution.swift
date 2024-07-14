class Solution {
    func groupAnagrams(_ strs: [String]) -> [[String]] {
        var d = [String: [String]]()
        for s in strs {
            let t = String(s.sorted())
            d[t, default: []].append(s)
        }
        return Array(d.values)
    }
}

class Solution {
    private var graph = [String: [String]]()
    private var count = [String: Int]()
    private var visited = Set<String>()
    private var freq: Int = 0

    func trulyMostPopular(_ names: [String], _ synonyms: [String]) -> [String] {
        for pair in synonyms {
            let cleanPair = pair.dropFirst().dropLast()
            let parts = cleanPair.split(separator: ",").map(String.init)
            let a = parts[0], b = parts[1]
            graph[a, default: []].append(b)
            graph[b, default: []].append(a)
        }

        var namesSet = Set<String>()
        for name in names {
            let index = name.firstIndex(of: "(")!
            let realName = String(name[..<index])
            namesSet.insert(realName)
            let num = Int(name[name.index(after: index)..<name.index(before: name.endIndex)])!
            count[realName] = num
        }

        var result = [String]()
        for name in namesSet {
            if !visited.contains(name) {
                freq = 0
                let representative = dfs(name)
                result.append("\(representative)(\(freq))")
            }
        }

        return result
    }

    private func dfs(_ name: String) -> String {
        var minName = name
        visited.insert(name)
        freq += count[name, default: 0]
        for neighbor in graph[name, default: []] {
            if !visited.contains(neighbor) {
                let temp = dfs(neighbor)
                if temp < minName {
                    minName = temp
                }
            }
        }
        return minName
    }
}

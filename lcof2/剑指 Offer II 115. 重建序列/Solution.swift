class Solution {
    func sequenceReconstruction(_ nums: [Int], _ sequences: [[Int]]) -> Bool {
        let n = nums.count
        var indegree = [Int](repeating: 0, count: n)
        var graph = Array(repeating: [Int](), count: n)

        for sequence in sequences {
            for i in 1..<sequence.count {
                let a = sequence[i - 1] - 1
                let b = sequence[i] - 1
                graph[a].append(b)
                indegree[b] += 1
            }
        }

        var queue = [Int]()
        for i in 0..<n {
            if indegree[i] == 0 {
                queue.append(i)
            }
        }

        while queue.count == 1 {
            let i = queue.removeFirst()
            for neighbor in graph[i] {
                indegree[neighbor] -= 1
                if indegree[neighbor] == 0 {
                    queue.append(neighbor)
                }
            }
        }

        return queue.isEmpty
    }
}
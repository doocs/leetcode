class Solution {
    func findOrder(_ numCourses: Int, _ prerequisites: [[Int]]) -> [Int] {
        var graph = Array(repeating: [Int](), count: numCourses)
        var indegree = Array(repeating: 0, count: numCourses)
        
        for prereq in prerequisites {
            let course = prereq[0]
            let prereqCourse = prereq[1]
            graph[prereqCourse].append(course)
            indegree[course] += 1
        }
        
        var queue = [Int]()
        for i in 0..<numCourses {
            if indegree[i] == 0 {
                queue.append(i)
            }
        }
        
        var order = [Int]()
        while !queue.isEmpty {
            let course = queue.removeFirst()
            order.append(course)
            
            for nextCourse in graph[course] {
                indegree[nextCourse] -= 1
                if indegree[nextCourse] == 0 {
                    queue.append(nextCourse)
                }
            }
        }
        
        return order.count == numCourses ? order : []
    }
}
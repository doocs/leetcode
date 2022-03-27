class Solution:
    def scheduleCourse(self, courses: List[List[int]]) -> int:
        courses.sort(key=lambda x: x[1])
        pq = []
        s = 0
        for d, e in courses:
            heappush(pq, -d)
            s += d
            if s > e:
                s += heappop(pq)
        return len(pq)

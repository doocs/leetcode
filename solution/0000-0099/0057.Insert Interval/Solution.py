class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        def merge(intervals: List[List[int]]) -> List[List[int]]:
            intervals.sort(key=lambda x: x[0])
            st = ed = -1
            res = []
            for s, e in intervals:
                if ed < s:
                    if st != -1:
                        res.append([st, ed])
                    st, ed = s, e
                else:
                    ed = max(ed, e)
            if st != -1:
                res.append([st, ed])
            return res

        intervals.append(newInterval)
        return merge(intervals)

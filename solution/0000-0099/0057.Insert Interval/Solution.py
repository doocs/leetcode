class Solution:
    def insert(
        self, intervals: List[List[int]], newInterval: List[int]
    ) -> List[List[int]]:
        def merge(intervals):
            intervals.sort()
            ans = []
            st, ed = intervals[0]
            for s, e in intervals[1:]:
                if ed < s:
                    ans.append([st, ed])
                    st, ed = s, e
                else:
                    ed = max(ed, e)
            ans.append([st, ed])
            return ans

        intervals.append(newInterval)
        return merge(intervals)

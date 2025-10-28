/*
// Definition for an Interval.
class Interval {
public:
    int start;
    int end;

    Interval() {}

    Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
public:
    vector<Interval> employeeFreeTime(vector<vector<Interval>> schedule) {
        vector<Interval> intervals;
        for (auto& e : schedule) {
            intervals.insert(intervals.end(), e.begin(), e.end());
        }

        sort(intervals.begin(), intervals.end(), [](const Interval& a, const Interval& b) {
            if (a.start == b.start) return a.end < b.end;
            return a.start < b.start;
        });

        vector<Interval> merged;
        merged.push_back(intervals[0]);
        for (int i = 1; i < intervals.size(); ++i) {
            auto& last = merged.back();
            auto& cur = intervals[i];
            if (last.end < cur.start) {
                merged.push_back(cur);
            } else {
                last.end = max(last.end, cur.end);
            }
        }

        vector<Interval> ans;
        for (int i = 1; i < merged.size(); ++i) {
            auto& a = merged[i - 1];
            auto& b = merged[i];
            ans.emplace_back(a.end, b.start);
        }

        return ans;
    }
};

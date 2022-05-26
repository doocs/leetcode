/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */
bool cmp(Interval &val1,Interval &val2){
    return !(val1.start >= val2.start);
}

class Solution {
public:
    vector<Interval> merge(vector<Interval>& intervals) {
       
        int len = intervals.size();
        if(len <= 1)return intervals;
        
        sort(intervals.begin(),intervals.end(),cmp);
        
        vector<Interval> ans;
        ans.push_back(intervals[0]);
        
        for(int i = 1;i<len;i++){
            if(ans.back().end >= intervals[i].start){
                ans.back().end = max(ans.back().end,intervals[i].end);
            }
            else{
                ans.push_back(intervals[i]);
            }
        }
        return ans;
    }
};
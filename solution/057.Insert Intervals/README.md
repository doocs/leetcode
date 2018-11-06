
## 插入区间
### 问题描述

给出一个**无重叠**的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

```
示例 1:
输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]

示例 2:
输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
```

### 思路：

先插入，之后和56题合并区间一样(竟然打败了96%的人，距离top1峰差一点优化)

```CPP
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
    return val1.start < val2.start;
}

class Solution {
public:
    vector<Interval> insert(vector<Interval>& intervals, Interval newInterval) {
        //先插进去，重复56题
        
        intervals.push_back(newInterval);
        
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
```


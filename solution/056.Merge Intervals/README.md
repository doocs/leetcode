给出一个区间的集合，请合并所有重叠的区间。
```
示例 1:
输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2:
输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
```

-------------------
思路：

1. 对容器按start值从小到大排序
2. 两两顺序比较，用第一个元素的end值和第二个元素的start值比较
3. 如果后start比前end小，且前end比后end小，则合并！
4. 不满足3，则直接插入

时间复杂度O(n)

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
```
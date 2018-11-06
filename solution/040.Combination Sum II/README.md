## 组合总和2

### 题目描述

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 

```
示例 1:
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

示例 2:
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

### 思路

和39题一模一样，注意他有重复数，需要去除重复的结果.

还要注意回溯是往后回溯，不是原地回溯了

```CPP
class Solution {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int>> ans;
        vector<int> tmp;
        sort(candidates.begin(),candidates.end());
        int len = candidates.size();
        
        dfs(ans,tmp,candidates,target,len,0);
        
        return ans;
    }
    
    void dfs(vector<vector<int>> &ans,vector<int> &tmp,vector<int> &nums,int target,int len,int index) {
        
        if(target == 0){
            auto iter = find(ans.begin(),ans.end(),tmp);
            if(iter == ans.end())ans.push_back(tmp);
        }

        for(int i = index;i<len && target >= nums[i];i++){
            tmp.push_back(nums[i]);
            dfs(ans,tmp,nums,target - nums[i],len,i+1);//注意i+1
            tmp.pop_back();
        }    
    }
};
```
## 组合总和

### 问题描述

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：
所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
```
示例 1:
输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]


示例 2:
输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```
### 思路

这种题肯定是用回溯递归的，和46题全排列那道题很像

[1,2,3,4]构建成回溯树如下状态，一次循环开始进入一个数，一次循环后pop出来一个数，形成一种对称性回溯
```
        1
      / | \
    12  13  14
  /  |
123 124 ..... 
```
### CPP
```CPP
class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        int len = candidates.size();
        vector<vector<int>> ans;
        vector<int> tmp;
        sort(candidates.begin(),candidates.end());
        dfs(ans,tmp,candidates,target,len,0);
        
        return ans;
    }
    
    void dfs(vector<vector<int>> &ans,vector<int> &tmp,vector<int> &nums,int target,int len,int index){
        
        if(target == 0) ans.push_back(tmp);
        
        for(int i = index;i<len && target >= nums[i];i++){
            tmp.push_back(nums[i]);
            dfs(ans,tmp,nums,target-nums[i],len,i);
            tmp.pop_back();
        }
    }
};
```
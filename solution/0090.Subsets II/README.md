## 子集 II

### 问题描述

给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

```
示例:
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```


### 思路

回溯+排序去重

```CPP
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        vector<int> tmp;
        vector<vector<int>> ans;
        int len = nums.size();
        if(len == 0)return ans;
        dfs(nums,ans,tmp,len,0);
        return ans;
    }
    
    void dfs(vector<int> &nums,vector<vector<int>> &ans,vector<int> tmp,int len,int k){
        sort(tmp.begin(),tmp.end());
        auto iter = find(ans.begin(),ans.end(),tmp);
        if(iter == ans.end())ans.push_back(tmp);
        for(int i = k;i<len;i++){
            tmp.push_back(nums[i]);
            dfs(nums,ans,tmp,len,i+1);
            tmp.pop_back();
        }
    }
};
```
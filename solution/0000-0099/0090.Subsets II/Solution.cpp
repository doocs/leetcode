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
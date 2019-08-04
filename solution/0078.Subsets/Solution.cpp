class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> tmp;
        int n = nums.size();
        dfs(ans,nums,tmp,0,n);
        
        return ans;
    }
    
    
    void dfs(vector<vector<int>> &ans,vector<int>& nums,vector<int> &tmp,int k,int n){
        ans.push_back(tmp);
        for(int i = k;i < n;i++)
        {
            tmp.push_back(nums[i]);
            dfs(ans,nums,tmp,i+1,n);
            tmp.pop_back();
        }
    }
};
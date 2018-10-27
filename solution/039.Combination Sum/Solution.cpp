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
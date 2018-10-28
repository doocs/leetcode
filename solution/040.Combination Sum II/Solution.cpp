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
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int len = nums.size();
        vector<int> ans;
        if(len == 0)return ans;
        
        int index;
        for(int i = 0;i<len;++i){
            index = abs(nums[i]) - 1;
            
            if(nums[index] > 0)
                nums[index] = -nums[index];   
        }

        
        for(int i = 0;i<len;++i){
            if(nums[i] > 0)
                ans.push_back(i+1);
        }
        
        return ans;
    }
};
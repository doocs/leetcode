class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int len = nums.size();
        vector<vector<int>> ans;
        if(len < 4)return ans;
        sort(nums.begin(),nums.end());
        
        int left;
        int right;
        int sum;
        for(int i = 0;i<len;i++){
            if(i> 0 && nums[i] == nums[i-1])continue;
            for(int j = i + 1;j<len;j++){
                if(j > i+1 && nums[j] == nums[j-1])continue;
                
                left = j+1;
                right = len - 1;
                
                while(left < right){
                    
                    sum = nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if(sum == target){
                        vector<int> tmp{nums[i],nums[j],nums[left],nums[right]};
                        ans.push_back(tmp);
                        
                        while(left < right && nums[left] == nums[left+1])left++;
                        while(left < right && nums[right] == nums[right-1])right--;
                        left++;
                        right--;
                    }
                    else if(sum < target)left++;
                    else if(sum > target)right--;
                }       
            }
        }
        return ans;
    }
};

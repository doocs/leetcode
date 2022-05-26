class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(),nums.end());

        vector<vector<int>> ans;
        
        int sum;
        int len = nums.size();
        int left,right;
        for(int i = 0; i< len;i++){
            left = i + 1;
            right = len - 1;
            while(left < right){
                sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    vector<int> vec;
                    vec.push_back(nums[i]);
                    vec.push_back(nums[left]);
                    vec.push_back(nums[right]);
                    ans.push_back(vec);
                    
                    while(left < right && nums[left] == nums[left + 1])left++;
                    while(left < right && nums[right] == nums[right - 1])right--;
                    
                    left++;
                    right--;
                    
                }
                if(sum > 0)right--;
                if(sum < 0)left++;
            }
            
            while(i<len-1 && nums[i] == nums[i+1])i++;
        }
        
        return ans;
        
    }
};
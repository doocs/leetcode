class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        int len = nums.size();
        if(len == 0)return {};
        vector<string> ans;
        int count = 0;
        int idx = 0;
        while((idx + count) < len-1){
            if(nums[idx+count] == nums[idx+count+1]-1)count++;
            else{
                string str;
                if(count == 0){
                    str = to_string(nums[idx]);
                }
                else{
                    str = to_string(nums[idx])+"->"+to_string(nums[idx+count]);
                }
                ans.push_back(str);
                idx += (count+1);
                count = 0;
            }
        }
        
        //末尾处理
        string str;
        if(count > 0)
            str = to_string(nums[idx])+"->"+to_string(nums[idx+count]);
        else
            str = to_string(nums[idx]);
            
        ans.push_back(str);
        
        return ans;
        
    }
};
class Solution {
public:
    void sortColors(vector<int>& nums) {
        int l=0,zero=0,r=nums.size()-1;//l为当前处理位置，如果l的值为0，和zero交换，如果为2和r位置的值交换；如果为1那么l++；类似于sliding window
        while(l<=r)
        {
            if(nums[l]==0)
                swap(nums[l++],nums[zero++]);
            else if(nums[l]==2)
                swap(nums[l],nums[r--]);
            else
                l++;
            
        }
        
    }
};

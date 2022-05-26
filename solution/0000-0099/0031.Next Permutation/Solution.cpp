class Solution {
public:
    void nextPermutation(vector<int> &nums) {
	    int len = nums.size();

	    if (len == 0)return;
        int i,j;
        for(i = len - 2;i>=0;i--){
            if(nums[i+1] > nums[i]){
                for(j = len - 1;j>i;j--){
                    if(nums[j]>nums[i])break;
                }   
                swap(nums[i],nums[j]);
                reverse(nums.begin()+i+1,nums.end());           
                return;
            }
        }
	    reverse(nums.begin(),nums.end());
    }
};

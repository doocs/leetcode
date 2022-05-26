class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int len = nums.size();
        if(len == 0)return;
       
        int slow = 0;
        int fast;
        
        while(slow < len){
            if(nums[slow] == 0){
                fast = slow+1;
                while(fast < len){
                    if(nums[fast] == 0)fast++;
                    else break;
                }
                
                if(fast == len)return;
                
                swap(nums[slow],nums[fast]);
            }
            slow++;
        }
        
    }
};

//---------------------------------------------

class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int len = nums.size();
        if(len == 0)return;
       
        int idx = 0;
        for(int i = 0;i<len;i++){
            if(nums[i] != 0){
                nums[idx] = nums[i];
                idx++;
            }
        }
        
        for(int i = idx;i<len;i++){
            nums[i] = 0;
        }
    }
};

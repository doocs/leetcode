class Solution {
public:
    void sortColors(vector<int>& nums) {
        if(nums.empty())return ;
        
        int count[3] = {0};
        size_t len = nums.size();
        
        for(int i = 0;i<len;i++){
            count[nums[i]]++;
        }
        int index = 0;
        for(int i = 0;i<3;i++){
            while(count[i] != 0){
                nums[index++] = i;
                count[i]--;
            }
        }   
    }
};

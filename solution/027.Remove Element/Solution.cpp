class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int len = nums.size();
        if(len < 1)return 0;
        auto iter = find(nums.begin(),nums.end(),val);
        while(iter != nums.end())
        {
            nums.erase(iter);
            iter = find(nums.begin(),nums.end(),val);
        }
        len = nums.size();
        
        return len;
    }
};
--------------------------------------------------
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int len = nums.size();
        if(len < 1)return 0;
        
        int i = 0;
        while(i < len)
        {
            if(nums[i] == val){
                nums[i] = nums[len - 1];
                len--;
            }    
            else i++;
        }
        
        return len;
    }
};
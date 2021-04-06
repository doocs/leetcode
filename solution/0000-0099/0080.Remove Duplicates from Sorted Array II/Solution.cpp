class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if(nums.empty())return 0;
        size_t len = nums.size();
        if(len == 1)return 1;

        auto iter = nums.begin();
        iter++;
        int k = 1;
        while(iter != nums.end()){
            if(*iter == *(iter-1))k++;
            else k = 1;

            if(k==3){
                nums.erase(iter);
                k--;
            }
            else {
                iter++;
            }
        }

        len = nums.size();
        return len;
    }
};

/**
 *  Author: Moriarty12138
 */
 class Solution {
 public:
     int removeDuplicates(vector<int>& nums) {
         if(nums.size() < 3) {
             nums.size();
         }
         int ans = 0;
         for(int n : nums) {
             if(ans < 2 || nums[ans - 2] < n) {
                 nums[ans++] = n;
             }
         }
         return ans;
     }
 };

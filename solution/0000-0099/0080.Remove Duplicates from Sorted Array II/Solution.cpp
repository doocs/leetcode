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
// 三路partition算法(0ms)
class Solution {
public:
    void sortColors(vector<int>& nums) {
        vector<int>::iterator next_lower = nums.begin() ;
        vector<int>::iterator next_cmp = nums.begin() ; 
        vector<int>::iterator next_bigger = nums.end()-1 ;
        
        while (next_cmp <= next_bigger)
        {
                if (*next_cmp < 1)
                    swap(*next_lower++, *next_cmp++) ;
                else if (*next_cmp > 1)
                    swap(*next_bigger--, *next_cmp) ;
                else
                    ++next_cmp ;
        }
    }
};

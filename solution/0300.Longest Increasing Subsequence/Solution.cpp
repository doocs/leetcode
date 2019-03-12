class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        if (nums.size() == 0)
            return 0 ;
        int M[nums.size()] = {0, } ;
        int MM = -1 ;
        for (int i = nums.size()-1; i >= 0; --i)
        {
            for (int j = i+1; j < nums.size(); ++j)
                if (nums[i] < nums[j])
                    M[i] = max(M[i], M[j]) ;
            MM = max(++M[i], MM) ;
        }
        return MM ;
    }
};

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) 
    {
        vector<int> res ;
        unordered_map<int, int> hash ;
        
        for (int i = 0; i < nums.size(); ++i)
        {
            int aim = target - nums[i] ;
            int local = hash[aim] ; 
            if (local != NULL)
            {
                res.push_back(local-1) ;
                res.push_back(i) ;
                return res ;
            }
            else
                hash[nums[i]] = i+1 ;
        }

        return res ;
    }
};
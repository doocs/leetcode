class Solution {
private:
    inline vector<int> Fun(vector<int> &keys, int counts[])
    {
        vector<int> item ;
        for (int i = 0; i < keys.size(); ++i)
        {
            for (int j = 0; j < counts[i]; ++j)
                item.push_back(keys[i]) ;
        }
        return item ;
    }
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        if (nums.size() == 0)
            return {} ;
        vector<vector<int>> res ;
        
        sort(nums.begin(), nums.end()) ;
        const int len = nums.size() ;
        nums.push_back(nums[0] - 1) ; //哨兵
        
        vector<int> cnts ;
        vector<int> keys ;
        for (int i = 0, cnt = 1; i < len; ++i)
        {
            if (nums[i] != nums[i+1])
            {
                keys.push_back(nums[i]) ;
                cnts.push_back(cnt) ;
                // cout << "[" << nums[i] << "]: " << cnt << endl ;
                cnt = 1 ;
            }
            else
                ++cnt ;
        }
        
        int counts[cnts.size()] = {0, } ;
        int i ;
        while (true)
        {
            res.push_back(Fun(keys, counts));
            ++counts[0] ;
            for (i = 0; i < cnts.size(); ++i)
            {
                if (counts[i] > cnts[i])
                {
                    if (i+1 == cnts.size())
                    {
                        ++i ;
                        break ;
                    }
                    ++counts[i+1] ;
                    counts[i] = 0 ;
                }
                else
                    break ;
            }
            if (i >= cnts.size())
                break ;
        }
        return res ;
    }
};

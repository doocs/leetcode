class Solution {
private:
    void my_next_permutation(vector<int>::iterator b, vector<int>::iterator e)
    {
        // 指针从后往前移动找到第一个下降值， (如 1, 3, 2 的1为下降值)
        // 在这个下降值(1)后面找一个比下降值大的最小值(2)，与下降值(1)交换
        // 交换后原来下降值之后的位置做排序(2, 1, 3)
        vector<int>::iterator p = e-2 ;
        while (p >= b && *p >= *(p+1))
            --p ;
        
        if (p >= b)
        {
            int M = *p ;
            vector<int>::iterator pnindex = p ;
            for (vector<int>::iterator pn = p+1 ; pn < e; ++pn)
                if (*pn > *p)
                {
                    M = min(M, *pn) ;
                    pnindex = pn ;
                }
            swap(*p, *pnindex) ;
            sort(p+1, e) ;
        }
        else
            sort(b, e) ;
    }
    inline bool IsSample(vector<int> &a, vector<int> &b)
    {
        // 长度肯定一样，就不用比较长度了
        for (int i = a.size()-1; i >= 0; --i)
            if (a[i] != b[i])
                return false ;
        return true ;
    }
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        vector<int> nums_bak(nums) ;
        vector<vector<int>> res ;
        
        do
        {
            res.push_back(nums) ;
            my_next_permutation(nums.begin(), nums.end()) ;
        } while ( !IsSample(nums, nums_bak) ) ;
        
        return res ;
    }
};

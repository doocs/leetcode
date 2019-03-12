class Solution {
private:
    void my_next_permutation(vector<int>::iterator b, vector<int>::iterator e)
    {
        // 指针从后往前移动找到第一个下降值， (如 1, 3, 2 的1为下降值)
        // 在这个下降值(1)后面找一个比下降值大的最小值(2)，与下降值(1)交换
        // 交换后原来下降值之后的位置做排序(2, 1, 3)
        vector<int>::iterator p = e-2 ;
        while (p >= b && *p > *(p+1))
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
public:
    vector<vector<int>> permute(vector<int>& nums) {
        int n = nums.size() ;
        int cnt = n ;
        while (--n)
            cnt *= n ;
        
        vector<vector<int>> res ;
        while (cnt--)
        {
            res.push_back(nums) ;
            // next_permutation(nums.begin(), nums.end()) ;
            my_next_permutation(nums.begin(), nums.end()) ;
        }
        return res ;
    }
};

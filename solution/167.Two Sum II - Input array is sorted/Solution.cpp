// 先看target是否是偶数，
// 偶数的话先排除numbers[i] == numbers[i+1] == target/2
// 然后set查找
// 存在的话 再折半查找定位
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        set<int> s(numbers.begin(), numbers.end()) ;
        
        int t = target ;
        if (!(target & 1))
            t /= 2 ;
        
        for (int i = 0; i < numbers.size()-1; ++i)
        {
            if (numbers[i] == t && numbers[i] == numbers[i+1])
                return {i+1, i+2} ;
            
            int tar = target - numbers[i] ;
            if (s.find(tar) != s.end())
            {
                int l = i+1, r = numbers.size(), mid ;
                while (l < r)
                {
                    mid = (l+r) >> 1 ;
                    if (numbers[mid] > tar)
                        r = mid ;
                    else if (numbers[mid] < tar)
                        l = mid+1 ;
                    else
                        break ;
                }
                return {i+1, mid+1} ;
            }
        }
        
        return {0, 0} ;
    }
};
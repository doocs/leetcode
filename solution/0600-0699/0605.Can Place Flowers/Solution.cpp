class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int i = 0, j = flowerbed.size()-1 ;
        int cnt=0 ;
        int num ;
        while (i <= j && !flowerbed[i])
            ++i ;
        if (i > j)
            return n <= 1 + (j>>1) ;
        num = i>>1 ;
        while (!flowerbed[j])
            --j ;
        num += (flowerbed.size()-1-j)>>1 ;
        //cout << i << ' ' << j << endl ;
        while (i <= j)
        {
            
        //cout << "num = " << num << ", cnt = " << cnt << endl ;
            if (flowerbed[i])
            {
                if (cnt > 0)
                    num += (cnt-1) >> 1 ;
                cnt = 0 ;
            }
            else
                cnt++ ;
            ++i ;
        }
        //cout << num << endl ;
        return num >= n ;
    }
};
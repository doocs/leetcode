class Solution {
public:
    int reverse(int x) {
       int r =0;
        while(x!=0)
        {
            int temp=x%10;
                        x=x/10;
            if(r>INT_MAX/10 || r<INT_MIN/10) return 0;
            r=r*10+temp;
        }
        return r;
    }
};
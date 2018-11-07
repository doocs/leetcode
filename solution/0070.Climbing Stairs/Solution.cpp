class Solution {
public:
    int climbStairs(int n) {
        
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        int arr[n]={0};
        arr[0]=1;
        arr[1]=2;
        for(int i=2;i<n;i++)
            arr[i]=arr[i-1]+arr[i-2];
        return arr[n-1];
        
    }
};

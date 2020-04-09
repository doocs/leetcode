class Solution {
public:
    int uniquePaths(int m, int n) {
        if(m<=0||n<=0)
            return 0;
        if(m==1||n==1)
            return 1;
        int arr[m][n]={0};
        arr[0][0]=1;
        for(int i=1;i<m;i++)
            arr[i][0]=1;
        for(int j=1;j<n;j++)
            arr[0][j]=1;
        for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
            {
                arr[i][j]=arr[i-1][j]+arr[i][j-1];
            }
        return arr[m-1][n-1];
    }
};

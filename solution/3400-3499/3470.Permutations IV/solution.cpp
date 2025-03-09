class Solution {
    long long f[105];
public:
    vector<int> permute(int n, long long k) {
        int i,j;
        for(i=f[0]=1;i<=n;i++)if(f[i-1]>=k)f[i]=k;
        else f[i]=f[i-1]*(i+1>>1);
        if(!(n&1))f[n]*=2;
        if(f[n]<k)return {};
        k--;
        vector<int> ans(n),a[2];
        for(i=0;i<n;i++)a[i&1].push_back(i);
        if(n&1)
        {
            ans[0]=k/f[n-1]*2;
            k-=ans[0]/2*f[n-1];
        }
        else
        {
            ans[0]=k/f[n-1];
            k-=ans[0]*f[n-1];
        }
        a[ans[0]&1].erase(lower_bound(a[ans[0]&1].begin(),a[ans[0]&1].end(),ans[0]));
        for(i=1;i<n;i++)
        {
            if(n&1)
            {
                ans[i]=a[i&1][k/f[n-i-1]];
            }
            else
            {
                ans[i]=a[(ans[0]^i)&1][k/f[n-i-1]];
            }
            k%=f[n-i-1];
            a[ans[i]&1].erase(lower_bound(a[ans[i]&1].begin(),a[ans[i]&1].end(),ans[i]));
        }
        for(i=0;i<n;i++)ans[i]++;
        return ans;
    }
};

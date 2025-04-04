class Solution {
public:
#define ll long long
	ll f[100003][23];
    long long minOperations(vector<int>& a, int x, int k) {
    	int n=a.size();
    	for(int j=0; j<=n; ++j)
    		for(int i=0; i<=k; ++i)
    			f[j][i]=1e18;
    	f[0][0]=0;
    	multiset<int> A,B;
    	ll as=0,bs=0;
    	for(int i=0; i<n; ++i)
    	{
    		if(B.empty()) as+=a[i],A.insert(a[i]);
    		else if(A.empty()) bs+=a[i],B.insert(a[i]);
    		else if(a[i]<=*A.rbegin()) as+=a[i],A.insert(a[i]);
    		else bs+=a[i],B.insert(a[i]);
    		if(i-x>=0)
    		{
    			if(A.find(a[i-x])!=A.end())
    			{
    				as-=a[i-x],A.erase(A.find(a[i-x]));
    			}
    			else
    			{
    				bs-=a[i-x],B.erase(B.find(a[i-x]));
    			}
    		}
    		int sa=A.size(),sb=B.size();
    		while(sa-sb>=2)
    		{
    			int o=*A.rbegin();
    			as-=o,bs+=o;
    			B.insert(*A.rbegin()),A.erase(prev(A.end()));
    			--sa,++sb;
    		}
    		while(sa<sb)
    		{
    			int o=*B.begin();
    			bs-=o,as+=o;
    			A.insert(*B.begin()),B.erase(B.begin());
    			++sa,--sb;
    		}
    		//3 2
    		//3 3
    		for(int j=0; j<=k; ++j)
    			f[i+1][j]=f[i][j];
    		if(i>=x-1)
    		{
    			ll Z=*A.rbegin();
    			ll cost=Z*A.size()-as+bs-Z*B.size();
                for(int j=1; j<=k; ++j)
	    			f[i+1][j]=min(f[i+1][j],f[i-x+1][j-1]+cost);
    		}
    	}
        return f[n][k];
    }
};
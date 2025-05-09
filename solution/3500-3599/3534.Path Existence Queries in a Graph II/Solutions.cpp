class Solution {
    public:
        vector<int> pathExistenceQueries(int n,
                                         vector<int>& nums,
                                         int maxDiff,
                                         vector<vector<int>>& queries) {
            vector<pair<int,int>> A(n);
            for(int i=0;i<n;i++) A[i]={nums[i],i};
            sort(A.begin(),A.end());
            
            vector<int> pos(n);
            for(int i=0;i<n;i++) pos[A[i].second]=i;
            
            vector<int> R(n);
            int r = 0;
            for(int l=0; l<n; l++) {
                while(r+1<n && A[r+1].first - A[l].first <= maxDiff) r++;
                R[l] = r;
            }
            
            int LOG = 0;
            while((1<<LOG) <= n) LOG++;
            vector<vector<int>> jump(LOG, vector<int>(n));
            for(int i=0;i<n;i++) jump[0][i]=R[i];
            for(int k=1;k<LOG;k++){
                for(int i=0;i<n;i++){
                    jump[k][i] = jump[k-1][ jump[k-1][i] ];
                }
            }
            
            auto minHops = [&](int p, int q)->int {
                if(p>q) return INT_MAX/2;
                if(p==q) return 0;
                int hops = 0;
                int cur = p;
                for(int k=LOG-1;k>=0;k--){
                    int nxt = jump[k][cur];
                    if(nxt < q) {
                        hops += (1<<k);
                        cur = nxt;
                    }
                }
                if(R[cur] < q) return INT_MAX/2;
                return hops+1;
            };
            
            vector<int> ans;
            ans.reserve(queries.size());
            for(auto &qr: queries){
                int u=qr[0], v=qr[1];
                int pu=pos[u], pv=pos[v];
                if(pu>pv) swap(pu,pv);
                int h = minHops(pu,pv);
                ans.push_back(h >= INT_MAX/2 ? -1 : h);
            }
            return ans;
        }
    };
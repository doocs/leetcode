class Solution {
    public:
        bool isPrintable(vector<vector<int>>& targetGrid) {
            int m = targetGrid.size(), n = targetGrid[0].size();
    
            const int MAXC = 60;
            vector<bool> seen(MAXC+1,false);
            vector<int> minR(MAXC+1, m), maxR(MAXC+1, -1);
            vector<int> minC(MAXC+1, n), maxC(MAXC+1, -1);
            
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    int c = targetGrid[i][j];
                    seen[c] = true;
                    minR[c] = min(minR[c], i);
                    maxR[c] = max(maxR[c], i);
                    minC[c] = min(minC[c], j);
                    maxC[c] = max(maxC[c], j);
                }
            }
            
            vector<bitset<MAXC+1>> adj(MAXC+1);
            vector<int> indeg(MAXC+1,0);
            for(int c=1;c<=MAXC;c++){
                if(!seen[c]) continue;
                for(int i=minR[c]; i<=maxR[c]; i++){
                    for(int j=minC[c]; j<=maxC[c]; j++){
                        int d = targetGrid[i][j];
                        if(d!=c && !adj[c].test(d)){
                            adj[c].set(d);
                            indeg[d]++;
                        }
                    }
                }
            }
            
            // Kahn's algorithm on at most 60 nodes
            queue<int> q;
            int totalColors = 0;
            for(int c=1;c<=MAXC;c++){
                if(!seen[c]) continue;
                totalColors++;
                if(indeg[c]==0) q.push(c);
            }
            
            int seenCount = 0;
            while(!q.empty()){
                int u = q.front(); q.pop();
                seenCount++;
                for(int v=1;v<=MAXC;v++){
                    if(adj[u].test(v) && --indeg[v]==0){
                        q.push(v);
                    }
                }
            }
         
            return seenCount == totalColors;
        }
    };
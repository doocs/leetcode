class Solution{
public;
  int count=0;
  vector<int> dfn,low;
  vector<vector<int>> graph;
  vector<vector<int>> res;
  /*tarjan算法更新DFN与low的规则：

tarjan(int u,int fa);

1.每次调用tarjan时，将DFN[u]与low[u]置为++count；

2.遍历u的相邻节点v，若v是u的父亲节点fa，则跳过这个v；若v未被访问过，则递归调用tarjan(v,u)；
若v已被访问过，说明出现了「环」，可以回溯u的low值，将u的low值更新为min(DFN[v],low[u]);

3.遍历完u的所有相邻节点后，将low[u]更新为min(low[u],low[v]),意义在于将同一连通分量内可以回溯到的最早节点统；
并对比DFN[u]与low[v]的大小，得到「桥」。*/
  int tarjan(int u,int fa){
    dfn[u]=low[u]=++count;
    for(auto& v:graph[u]){
      if(v==fa)
        continue;
      if(!dfn[v]){
        tarjan(v,u);
        low[u]=min(low[u],low[v]);
        if(dfn[u]<low[v])//u的子节点中不存在能够回溯到在u之前出现的节点的边
          res.push_back({u,v});
      }
      else{
        low[u]=min(dfn[v],low[u]);
      }
    }
  }
  
  vector<vector<int>> criticalConnections(int n,vector<vector<int>& connections){
    dfn.resize(n);
    low.resize(n);
    graph.resize(n);
    for(auto& edge:connections){
      graph[edge[0]].push_back(edge[1]);
      graph[edge[1]].push_back(edge[0]);
    }
    for(int i=0;i<n;i++){
      if(!dfn[i])
        tarjan(i,-1);
    }
    return res;
  }
};
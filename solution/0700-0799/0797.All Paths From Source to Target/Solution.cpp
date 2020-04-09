class Solution {
private:
    vector<vector<int>> paths ;
    vector<bool> visited = vector<bool>(16, false) ;
    vector<int> path ;
    
    void search(vector<vector<int>>& graph, int s, int e)
    {
        if (visited.at(s))
            return ;
        
        if (s == e)
        {
            path.push_back(e) ;
            paths.push_back(path) ;
            path.pop_back() ;
            return ;
        }
        
        visited[s] = true ;  // visited
        path.push_back(s) ;
        for (auto to: graph[s])
            search(graph, to, e) ;
        path.pop_back() ;
        visited[s] = false ;
        
    }
    
public:
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        if (graph.size() == 0)
            return {} ;
        
        search(graph, 0, graph.size()-1) ;
        
        return paths ;
    }
};
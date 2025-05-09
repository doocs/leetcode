#include <vector>
#include <unordered_map>
#include <stack>
using namespace std;

class Solution {
public:
    vector<vector<int>> validArrangement(vector<vector<int>>& pairs) {
        unordered_map<int, stack<int>> graph;
        unordered_map<int, int> out_degree;
        
        for (auto& p : pairs) {
            graph[p[0]].push(p[1]);
            out_degree[p[0]]++;
            out_degree[p[1]]--;
        }
        
        int start = pairs[0][0];
        for (auto& [node, degree] : out_degree) {
            if (degree > 0) {
                start = node;
                break;
            }
        }
        
        vector<vector<int>> result;
        stack<int> path;
        path.push(start);
        
        while (!path.empty()) {
            int current = path.top();
            if (!graph[current].empty()) {
                path.push(graph[current].top());
                graph[current].pop();
            } else {
                if (path.size() > 1) {
                    int end = path.top(); path.pop();
                    result.push_back({path.top(), end});
                } else {
                    path.pop();
                }
            }
        }
        
        reverse(result.begin(), result.end());
        return result;
    }
};
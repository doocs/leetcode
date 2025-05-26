class Solution {
public:
    int deleteGreatestValue(vector<vector<int>>& grid) 
    {
        int n = grid.size();
        int m = grid[0].size(); 
        int answer = 0;
        
        for(int i=0;i<n;i++)
        {
            sort(grid[i].begin(),grid[i].end(),greater<>());
        }
        for(int i=0;i<m;i++)
        {
            int mx = INT_MIN;
            for(int j=0;j<n;j++)
            {
                mx = max(mx,grid[j][i]);  
            }
            answer += mx; 
        }
        return answer;
    }
};
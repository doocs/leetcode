class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        size_t rowNum = triangle.size();
        
        //特殊值处理
        if(rowNum == 0)return 0;
        if(rowNum == 1){
            if(triangle[0].empty())return 0;
            else return triangle[0][0];
        }
        
        for(int i = 1;i<rowNum;i++){
            for(int j = i;j>=0;j--){
                //边界处理
                if(j == 0){triangle[i][j] = triangle[i][j] + triangle[i-1][j];continue;}
                if(j == i){triangle[i][j] = triangle[i][j] + triangle[i-1][j-1];continue;}
                
                //一般处理
                triangle[i][j] = triangle[i][j] + min(triangle[i-1][j],triangle[i-1][j-1]);
            }
        }
        
        int ans = INT_MAX;
        for(auto v : triangle[rowNum-1]){
            if(ans > v)ans = v;
        } 
        return ans;
    }
};
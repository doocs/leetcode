class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> ans;
        
        for(int i = 0;i<numRows;i++){
            vector<int> tmp(i+1);
            tmp[0] = 1;//最左侧为1
            for(int j = 1;j<=i;j++){
                if(i == j)//最右侧为1
                {
                    tmp[j] = 1;
                    break;
                }
                tmp[j] = ans[i-1][j-1] + ans[i-1][j]; 
            }
            ans.push_back(tmp);
        }     
        return ans;  
    }
};
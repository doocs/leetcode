//Solution1
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        if(matrix.empty())return;
        //行数组，列数组
        int rowNum = matrix.size();
        int columnNum = matrix[0].size();
        vector<int> rowVec;
        vector<int> columnVec;
        
        
        for(int i = 0;i<rowNum;i++){
            for(int j = 0;j<columnNum;j++){
                if(matrix[i][j] == 0){
                    auto iter = find(rowVec.begin(),rowVec.end(),i);
                    if(iter == rowVec.end())rowVec.push_back(i);
                    
                    iter = find(columnVec.begin(),columnVec.end(),j);
                    if(iter == columnVec.end())columnVec.push_back(j);
                }
            }
        }
        
        rowNum = rowVec.size();
        if(rowNum == 0)return;
        
        int row;
        int column;
        
        //行处理
        for(int i = 0;i<rowNum;i++){
            row = rowVec[i];
            for(int j = 0;j<columnNum;j++){
                matrix[row][j] = 0;
            }
        }
        
        //列处理
        columnNum = columnVec.size();
        rowNum = matrix.size();
        for(int i = 0 ; i < columnNum;i++){
            column = columnVec[i];
            for(int j = 0;j<rowNum;j++){
                matrix[j][column] = 0;
            }
        }
        
    }
};

//---------------------------------------------------------------------
//Solution2

class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        if(matrix.empty()) return;
        int m = matrix.size();
        int n = matrix[0].size();
        bool row = false , column = false;
        
        
        for(int i = 0; i < m; i++)//判断第1列的0；
        {
            if(matrix[i][0] == 0)
            {
                column = true;
                break;
            }
        }
       for(int i = 0; i < n; i ++)//判断第1行的0；
        {
            if(matrix[0][i] == 0)
            {
                row = true;
                break;
            }
        }
        
        for(int i = 1; i < m;i++)
        {
            for(int j = 1; j < n;j++)
            {
                if(matrix[i][j] == 0)
                {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < m;i++)
        {
            for(int j = 1; j < n;j++)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
        }
        if(row) 
            for(int i = 0; i < n;i++)
                matrix[0][i] = 0;
        if(column) 
            for(int i = 0; i < m;i++)
                matrix[i][0] = 0;
        
        return;

        
    }
};
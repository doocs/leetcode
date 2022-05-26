class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int row=matrix.size();
        if(row==0)
        {
            vector<int> zero;
            zero.clear();
            return zero;
        }
        int col=matrix[0].size();
        if(row==1)
            return matrix[0];
        if(col==0)
        {
            vector<int> zero;
            zero.clear();
            return zero;
        }
        if(col==1)
        {
            vector<int> temp;
            for(int i=0;i<row;i++)
                temp.push_back(matrix[i][0]);
            return temp;
        }
        vector<int> result;
        result=matrix[0];//result存储第一行
        //temp=matirx.pop_back();
        for(int i=1;i<matrix.size()-1;i++)
        {
            result.push_back(matrix[i][matrix[0].size()-1]);//存储每行最后一个
        }
        for(int i=0;i<col;i++)
        {
            result.push_back(matrix[row-1][col-1-i]);//倒序存储最后一行
        }
        for(int i=1;i<row-1;i++)
        {
            result.push_back(matrix[row-i-1][0]);//倒序存储每一行第一个
            vector<int> zz(matrix[row-i-1].begin()+1,matrix[row-i-1].end()-1);//将中间行去除第一个和最后一个数
            matrix[row-i-1]=zz;
        }
        vector<vector<int>> m2(matrix.begin()+1,matrix.end()-1);//将matrix去除第一行和最后一行，递归调用
        //cout<<m2.size()<<"  "<<m2[0].size();
        vector<int> l=spiralOrder(m2);
        result.insert(result.end(),l.begin(),l.end());//将递归结果插入result后面
        return result;
        
    }
};

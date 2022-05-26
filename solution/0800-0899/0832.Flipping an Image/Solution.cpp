class Solution {
  public:
    vector<vector<int>> flipAndInvertImage(vector<vector<int>> &A) {

        int temp, Len = A[0].size();

        for (int i = 0; i < A.size(); i++)
        {
            for (int j = 0; j < ((Len + 1) / 2); j++)
            {
                temp = !A[i][j];
                A[i][j] = !A[i][Len - j - 1];
                A[i][Len - j - 1] = temp;
            }
        }
        return A;
    }
};

class Solution {
public:
    vector<int> sumEvenAfterQueries(vector<int>& A, vector<vector<int>>& queries) {

        vector<int> ans;

        int Sum = 0;
        for(int i = 0; i < A.size(); i++){
            if( ! ( A[i] & 1 ) )
                Sum += A[i];
        }

        int old;

        for(int i = 0; i < queries.size(); i++){
            
            old = A[queries[i][1]];
            A[queries[i][1]] = A[queries[i][1]] + queries[i][0];

            if( (old % 2 != 0) && (A[queries[i][1]] % 2 == 0) )
                Sum += A[queries[i][1]];
            else if( (old % 2 == 0) && (A[queries[i][1]] % 2 == 0) )
                Sum = Sum - old + A[queries[i][1]];
            else if( (old % 2 == 0) && (A[queries[i][1]] % 2 != 0) )
                Sum = Sum - old;

            ans.push_back(Sum);

        }

        return ans;
    }
};

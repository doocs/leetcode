class Solution {
public:
    int repeatedNTimes(vector<int>& A) {
        for(int i  = (A.size() >> 1) - 1; i < A.size(); ++i)
            for(int j = i+1; j < A.size(); ++j)
                if(A[i] == A[j])
                    return A[i];
        return A[0] ;
    }
};
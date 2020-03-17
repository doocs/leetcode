class Solution {
public:
    bool validMountainArray(vector<int>& A) {
        if (A.size() < 3)
            return false ;
        
        A.push_back(A[0]) ;
        int lastIndex = A.size()-2 ;
        int p ;
        for (p = 0; A[p] < A[p+1]; ++p)
            ;
        if (p == lastIndex || 0 == p)
            return false ;
        A[A.size()-1] = A[p] ;
        for (; A[p] > A[p+1]; ++p)
            ;
        
        return p == lastIndex ;   
    }
};

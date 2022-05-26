class Solution {
public:
    int repeatedNTimes(vector<int>& A) {
        unordered_set<int> us(A[0]) ;
        for(int i  = (A.size() >> 1) - 1; i < A.size(); ++i)
            if(us.find(A[i]) != us.end())
                return A[i] ;
            else
                us.insert(A[i]) ;
        return A[0] ;
    }
};
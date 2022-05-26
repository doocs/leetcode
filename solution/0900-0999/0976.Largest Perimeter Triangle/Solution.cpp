class Solution {
public:
    int largestPerimeter(vector<int>& A) {
        priority_queue<int> q(A.begin(), A.end()) ; // 大顶堆
        
        int a, b, c ;
        b = q.top() ;
        q.pop() ;
        c = q.top() ;
        q.pop() ;
        while ( !q.empty() )
        {
            a = b ;
            b = c ;
            c = q.top() ;
            q.pop() ;
            if ( b + c > a )
                return a + b + c ;
        }
        return 0 ;
    }
};

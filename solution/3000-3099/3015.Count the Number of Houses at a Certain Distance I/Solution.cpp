class Solution {
public:
    vector<int> countOfPairs(int n, int x, int y) {
        vector<int> result(n);
        for(int i = 0; i < n; i++){
            result[i] = (n - i - 1) * 2;
        }
        if(x > y) swap(x, y);
        if((y-x) <= 1){
            return result;
        }
        int end = (x+y) / 2 - 1;
        int start = (x+y)/2 + 1;
        for(int i = 1; i <= end; i++){
            for(int j = start; j <= n; j++){
                int dist_old = j - i;
                int dist_new = abs(x - i) + 1 + abs(y - j);
                if(dist_old > dist_new){
                    result[dist_new-1] += 2;
                    result[dist_old-1] -= 2;
                }
            }
        }
        return result;
    }
};

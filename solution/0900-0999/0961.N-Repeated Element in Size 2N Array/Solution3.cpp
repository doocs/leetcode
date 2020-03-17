class Solution {
public:
    int repeatedNTimes(vector<int>& A) {
        unordered_map<int,int> m;
        for(int x : A){
            m[x]++;
            if(m[x] > 1){
                return x;
            }
        }
        return -1;
    }
};

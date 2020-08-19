class Solution {
    unordered_map<int, int> tested; // map (start index * 1005 + steps) to longest length  
public:
    int longestArithSeqLength(vector<int>& A) {
        int res = 0;
        for (int i = 0; i < A.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                int diff = A[i] - A[j];
                if (tested.count(j*10005 + diff)) {
                    tested[i*10005+diff] = max(tested[i*10005+diff], 1+tested[j*10005 + diff]);
                } else {
                    tested[i*10005+diff] = max(tested[i*10005+diff], 2);
                }
                res = max(res, tested[i*10005+diff]);
            }
        }
        return res;
    }
};
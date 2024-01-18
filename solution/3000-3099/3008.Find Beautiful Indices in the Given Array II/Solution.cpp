class Solution {
public:
    vector<int> beautifulIndices(string s, string patternA, string patternB, int k) {
        vector<int> beautifulIndicesA = kmpSearch(s, patternA);
        vector<int> beautifulIndicesB = kmpSearch(s, patternB);

        sort(beautifulIndicesB.begin(), beautifulIndicesB.end());

        vector<int> result;
        for (int indexA : beautifulIndicesA) {
            int left = lower_bound(beautifulIndicesB.begin(), beautifulIndicesB.end(), indexA - k) - beautifulIndicesB.begin();
            int right = lower_bound(beautifulIndicesB.begin(), beautifulIndicesB.end(), indexA + k + patternB.length()) - beautifulIndicesB.begin();

            left = (left >= 0) ? left : -(left + 1);
            right = (right >= 0) ? right : -(right + 1);

            for (int indexB = left; indexB < right; indexB++) {
                if (abs(beautifulIndicesB[indexB] - indexA) <= k) {
                    result.push_back(indexA);
                    break;
                }
            }
        }

        return result;
    }

private:
    vector<int> kmpSearch(string text, string pattern) {
        vector<int> indices;
        vector<int> pi = computePrefixFunction(pattern);

        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && pattern[q] != text[i]) {
                q = pi[q - 1];
            }
            if (pattern[q] == text[i]) {
                q++;
            }
            if (q == pattern.length()) {
                indices.push_back(i - q + 1);
                q = pi[q - 1];
            }
        }

        return indices;
    }

    vector<int> computePrefixFunction(string pattern) {
        int m = pattern.length();
        vector<int> pi(m, 0);
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && pattern[k] != pattern[q]) {
                k = pi[k - 1];
            }
            if (pattern[k] == pattern[q]) {
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }
};

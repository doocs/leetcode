class Solution {
public:
    int videoStitching(vector<vector<int>>& clips, int T) {
        int maxEnding = 0;
        int count = 0;
        vector<int> maxEnd(T + 1, 0); // maxEnd[i] : maximum ending time of all videos that start by time i
        for (int i = 0; i < clips.size(); ++i) {
            if (clips[i][0] >= T) continue;
            maxEnd[clips[i][0]] = max(maxEnd[clips[i][0]], clips[i][1]);
        }
        for (int i = 1; i < T; ++i) {
            maxEnd[i] = max(maxEnd[i], maxEnd[i - 1]);
        }
        for (int i = 0; i < T; ++i) {
            if (maxEnding == i) { // select video with maximum ending if one more video is necessary
                count++;
                maxEnding = maxEnd[i];
            } else if (maxEnding < i) {
                return -1;
            }
        }
        return count;
    }
};
class TweetCounts {
public:
    TweetCounts() {
    }

    void recordTweet(string tweetName, int time) {
        data[tweetName].insert(time);
    }

    vector<int> getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime) {
        int f = 60;
        if (freq == "hour")
            f = 3600;
        else if (freq == "day")
            f = 86400;
        vector<int> ans((endTime - startTime) / f + 1);
        auto l = data[tweetName].lower_bound(startTime);
        auto r = data[tweetName].upper_bound(endTime);
        for (; l != r; ++l) {
            ++ans[(*l - startTime) / f];
        }
        return ans;
    }

private:
    unordered_map<string, multiset<int>> data;
};

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts* obj = new TweetCounts();
 * obj->recordTweet(tweetName,time);
 * vector<int> param_2 = obj->getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
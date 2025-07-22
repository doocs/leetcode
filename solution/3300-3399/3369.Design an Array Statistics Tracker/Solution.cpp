class MedianFinder {
public:
    void addNum(int num) {
        if (small.empty() || num <= small.top()) {
            small.push(num);
            ++smallSize;
        } else {
            large.push(num);
            ++largeSize;
        }
        reblance();
    }

    void removeNum(int num) {
        ++delayed[num];
        if (num <= small.top()) {
            --smallSize;
            if (num == small.top()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.top()) {
                prune(large);
            }
        }
        reblance();
    }

    int findMedian() {
        return smallSize == largeSize ? large.top() : small.top();
    }

private:
    priority_queue<int> small;
    priority_queue<int, vector<int>, greater<int>> large;
    unordered_map<int, int> delayed;
    int smallSize = 0;
    int largeSize = 0;

    template <typename T>
    void prune(T& pq) {
        while (!pq.empty() && delayed[pq.top()]) {
            if (--delayed[pq.top()] == 0) {
                delayed.erase(pq.top());
            }
            pq.pop();
        }
    }

    void reblance() {
        if (smallSize > largeSize + 1) {
            large.push(small.top());
            small.pop();
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < largeSize) {
            small.push(large.top());
            large.pop();
            ++smallSize;
            --largeSize;
            prune(large);
        }
    }
};

class StatisticsTracker {
private:
    queue<int> q;
    long long s = 0;
    unordered_map<int, int> cnt;
    MedianFinder medianFinder;
    set<pair<int, int>> ts;

public:
    StatisticsTracker() {}

    void addNumber(int number) {
        q.push(number);
        s += number;
        ts.erase({-cnt[number], number});
        cnt[number]++;
        medianFinder.addNum(number);
        ts.insert({-cnt[number], number});
    }

    void removeFirstAddedNumber() {
        int number = q.front();
        q.pop();
        s -= number;
        ts.erase({-cnt[number], number});
        cnt[number]--;
        if (cnt[number] > 0) {
            ts.insert({-cnt[number], number});
        }
        medianFinder.removeNum(number);
    }

    int getMean() {
        return static_cast<int>(s / q.size());
    }

    int getMedian() {
        return medianFinder.findMedian();
    }

    int getMode() {
        return ts.begin()->second;
    }
};

class Router {
private:
    int lim;
    unordered_set<long long> vis;
    deque<array<int, 3>> q;
    unordered_map<int, int> idx;
    unordered_map<int, vector<int>> d;

    long long f(int a, int b, int c) {
        return ((long long) a << 46) | ((long long) b << 29) | (long long) c;
    }

public:
    Router(int memoryLimit) {
        lim = memoryLimit;
    }

    bool addPacket(int source, int destination, int timestamp) {
        long long x = f(source, destination, timestamp);
        if (vis.count(x)) {
            return false;
        }
        vis.insert(x);
        if ((int) q.size() >= lim) {
            forwardPacket();
        }
        q.push_back({source, destination, timestamp});
        d[destination].push_back(timestamp);
        return true;
    }

    vector<int> forwardPacket() {
        if (q.empty()) {
            return {};
        }
        auto packet = q.front();
        q.pop_front();
        int s = packet[0], d_ = packet[1], t = packet[2];
        vis.erase(f(s, d_, t));
        idx[d_]++;
        return {s, d_, t};
    }

    int getCount(int destination, int startTime, int endTime) {
        auto& ls = d[destination];
        int k = idx[destination];
        auto i = lower_bound(ls.begin() + k, ls.end(), startTime);
        auto j = lower_bound(ls.begin() + k, ls.end(), endTime + 1);
        return j - i;
    }
};

/**
 * Your Router object will be instantiated and called as such:
 * Router* obj = new Router(memoryLimit);
 * bool param_1 = obj->addPacket(source,destination,timestamp);
 * vector<int> param_2 = obj->forwardPacket();
 * int param_3 = obj->getCount(destination,startTime,endTime);
 */

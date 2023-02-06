int N;

int dist(const pair<int, int>& p) {
    auto [l, r] = p;
    if (l == -1 || r == N) return r - l - 1;
    return (r - l) >> 1;
}

struct cmp {
    bool operator()(const pair<int, int>& a, const pair<int, int>& b) const {
        int d1 = dist(a), d2 = dist(b);
        return d1 == d2 ? a.first < b.first : d1 > d2;
    };
};

class ExamRoom {
public:
    ExamRoom(int n) {
        N = n;
        this->n = n;
        add({-1, n});
    }

    int seat() {
        auto s = *ts.begin();
        int p = (s.first + s.second) >> 1;
        if (s.first == -1) {
            p = 0;
        } else if (s.second == n) {
            p = n - 1;
        }
        del(s);
        add({s.first, p});
        add({p, s.second});
        return p;
    }

    void leave(int p) {
        int l = left[p], r = right[p];
        del({l, p});
        del({p, r});
        add({l, r});
    }

private:
    set<pair<int, int>, cmp> ts;
    unordered_map<int, int> left;
    unordered_map<int, int> right;
    int n;

    void add(pair<int, int> s) {
        ts.insert(s);
        left[s.second] = s.first;
        right[s.first] = s.second;
    }

    void del(pair<int, int> s) {
        ts.erase(s);
        left.erase(s.second);
        right.erase(s.first);
    }
};

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom* obj = new ExamRoom(n);
 * int param_1 = obj->seat();
 * obj->leave(p);
 */
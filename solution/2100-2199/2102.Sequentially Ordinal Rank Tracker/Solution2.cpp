using pis = pair<int, string>;

class SORTracker {
public:
    SORTracker() {
    }

    void add(string name, int score) {
        good.push({-score, name});
        bad.push(good.top());
        good.pop();
    }

    string get() {
        good.push(bad.top());
        bad.pop();
        return good.top().second;
    }

private:
    priority_queue<pis, vector<pis>, less<pis>> good;
    priority_queue<pis, vector<pis>, greater<pis>> bad;
};

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker* obj = new SORTracker();
 * obj->add(name,score);
 * string param_2 = obj->get();
 */
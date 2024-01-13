class SnakeGame {
public:
    SnakeGame(int width, int height, vector<vector<int>>& food) {
        m = height;
        n = width;
        this->food = food;
        score = 0;
        idx = 0;
        q.push_back(0);
        vis.insert(0);
    }

    int move(string direction) {
        int p = q.front();
        int i = p / n, j = p % n;
        int x = i, y = j;
        if (direction == "U") {
            --x;
        } else if (direction == "D") {
            ++x;
        } else if (direction == "L") {
            --y;
        } else {
            ++y;
        }
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return -1;
        }
        if (idx < food.size() && x == food[idx][0] && y == food[idx][1]) {
            ++score;
            ++idx;
        } else {
            int tail = q.back();
            q.pop_back();
            vis.erase(tail);
        }
        int cur = f(x, y);
        if (vis.count(cur)) {
            return -1;
        }
        q.push_front(cur);
        vis.insert(cur);
        return score;
    }

private:
    int m;
    int n;
    vector<vector<int>> food;
    int score;
    int idx;
    deque<int> q;
    unordered_set<int> vis;

    int f(int i, int j) {
        return i * n + j;
    }
};

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame* obj = new SnakeGame(width, height, food);
 * int param_1 = obj->move(direction);
 */
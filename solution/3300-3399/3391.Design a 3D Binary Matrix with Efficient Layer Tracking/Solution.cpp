class matrix3D {
private:
    vector<vector<vector<int>>> g;
    vector<int> cnt;
    set<pair<int, int>> sl;

public:
    matrix3D(int n) {
        g.resize(n, vector<vector<int>>(n, vector<int>(n, 0)));
        cnt.resize(n, 0);
    }

    void setCell(int x, int y, int z) {
        if (g[x][y][z] == 1) {
            return;
        }
        g[x][y][z] = 1;
        sl.erase({-cnt[x], -x});
        cnt[x]++;
        sl.insert({-cnt[x], -x});
    }

    void unsetCell(int x, int y, int z) {
        if (g[x][y][z] == 0) {
            return;
        }
        g[x][y][z] = 0;
        sl.erase({-cnt[x], -x});
        cnt[x]--;
        if (cnt[x]) {
            sl.insert({-cnt[x], -x});
        }
    }

    int largestMatrix() {
        return sl.empty() ? g.size() - 1 : -sl.begin()->second;
    }
};

/**
 * Your matrix3D object will be instantiated and called as such:
 * matrix3D* obj = new matrix3D(n);
 * obj->setCell(x,y,z);
 * obj->unsetCell(x,y,z);
 * int param_3 = obj->largestMatrix();
 */

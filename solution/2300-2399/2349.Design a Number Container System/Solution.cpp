class NumberContainers {
public:
    NumberContainers() {
    }

    void change(int index, int number) {
        if (d.contains(index)) {
            int oldNumber = d[index];
            g[oldNumber].erase(index);
            if (g[oldNumber].empty()) {
                g.erase(oldNumber);
            }
        }
        d[index] = number;
        g[number].insert(index);
    }

    int find(int number) {
        return g.contains(number) ? *g[number].begin() : -1;
    }

private:
    unordered_map<int, int> d;
    unordered_map<int, set<int>> g;
};

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers* obj = new NumberContainers();
 * obj->change(index,number);
 * int param_2 = obj->find(number);
 */
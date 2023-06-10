class Trie {
public:
    unordered_map<string, Trie*> children;
    int v;

    Trie(int v) {
        this->v = v;
    }

    bool insert(string& w, int v) {
        Trie* node = this;
        auto ps = split(w, '/');
        for (int i = 1; i < ps.size() - 1; ++i) {
            auto p = ps[i];
            if (!node->children.count(p)) {
                return false;
            }
            node = node->children[p];
        }
        if (node->children.count(ps.back())) {
            return false;
        }
        node->children[ps.back()] = new Trie(v);
        return true;
    }

    int search(string& w) {
        Trie* node = this;
        auto ps = split(w, '/');
        for (int i = 1; i < ps.size(); ++i) {
            auto p = ps[i];
            if (!node->children.count(p)) {
                return -1;
            }
            node = node->children[p];
        }
        return node->v;
    }

private:
    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }
};

class FileSystem {
public:
    FileSystem() {
        trie = new Trie(-1);
    }

    bool createPath(string path, int value) {
        return trie->insert(path, value);
    }

    int get(string path) {
        return trie->search(path);
    }

private:
    Trie* trie;
};

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem* obj = new FileSystem();
 * bool param_1 = obj->createPath(path,value);
 * int param_2 = obj->get(path);
 */
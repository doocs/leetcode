class Vector2D {
public:
    Vector2D(vector<vector<int>>& vec) {
        this->vec = move(vec);
    }
    
    int next() {
        forward();
        return vec[i][j++];
    }
    
    bool hasNext() {
        forward();
        return i < vec.size();
    }

private:
    int i = 0;
    int j = 0;
    vector<vector<int>> vec;

    void forward() {
        while (i < vec.size() && j >= vec[i].size()) {
            ++i;
            j = 0;
        }
    }
};

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D* obj = new Vector2D(vec);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
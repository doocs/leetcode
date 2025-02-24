class OrderedStream {
public:
    OrderedStream(int n) {
        ptr = 1;
        data = vector<string>(n + 1);
    }

    vector<string> insert(int idKey, string value) {
        data[idKey] = value;
        vector<string> ans;
        while (ptr < data.size() && !data[ptr].empty()) {
            ans.push_back(data[ptr++]);
        }
        return ans;
    }

private:
    int ptr;
    vector<string> data;
};

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream* obj = new OrderedStream(n);
 * vector<string> param_1 = obj->insert(idKey,value);
 */

class OrderedStream {
public:
    vector<string> data;
    int ptr = 0;

    OrderedStream(int n) {
        data.resize(n, "");
    }

    vector<string> insert(int idKey, string value) {
        data[idKey - 1] = value;
        vector<string> ans;
        while (ptr < data.size() && data[ptr] != "") ans.push_back(data[ptr++]);
        return ans;
    }
};

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream* obj = new OrderedStream(n);
 * vector<string> param_1 = obj->insert(idKey,value);
 */
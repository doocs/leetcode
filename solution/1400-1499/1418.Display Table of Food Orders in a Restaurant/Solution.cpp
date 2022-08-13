class Solution {
public:
    vector<vector<string>> displayTable(vector<vector<string>>& orders) {
        unordered_set<int> tables;
        unordered_set<string> foods;
        unordered_map<string, int> mp;
        for (auto& order : orders) {
            int table = stoi(order[1]);
            string food = order[2];
            tables.insert(table);
            foods.insert(food);
            ++mp[order[1] + "." + food];
        }
        vector<int> t;
        t.assign(tables.begin(), tables.end());
        sort(t.begin(), t.end());
        vector<string> f;
        f.assign(foods.begin(), foods.end());
        sort(f.begin(), f.end());
        vector<vector<string>> res;
        vector<string> title;
        title.push_back("Table");
        for (auto e : f) title.push_back(e);
        res.push_back(title);
        for (int table : t) {
            vector<string> tmp;
            tmp.push_back(to_string(table));
            for (string food : f) {
                tmp.push_back(to_string(mp[to_string(table) + "." + food]));
            }
            res.push_back(tmp);
        }
        return res;
    }
};
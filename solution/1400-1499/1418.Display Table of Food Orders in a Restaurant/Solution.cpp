class Solution {
public:
    vector<vector<string>> displayTable(vector<vector<string>>& orders) {
        map<int, vector<string>> tables;
        set<string> sortedItems;
        for (auto& o : orders) {
            int table = stoi(o[1]);
            string foodItem = o[2];
            tables[table].push_back(foodItem);
            sortedItems.insert(foodItem);
        }
        vector<vector<string>> ans;
        vector<string> header = {"Table"};
        header.insert(header.end(), sortedItems.begin(), sortedItems.end());
        ans.push_back(header);
        for (auto& [table, items] : tables) {
            unordered_map<string, int> cnt;
            for (string& item : items) {
                cnt[item]++;
            }
            vector<string> row;
            row.push_back(to_string(table));
            for (const string& item : sortedItems) {
                row.push_back(to_string(cnt[item]));
            }
            ans.push_back(row);
        }
        return ans;
    }
};

class SQL {
public:
    unordered_map<string, int> cols;
    unordered_map<string, map<int, vector<string>>> rows;
    unordered_map<string, int> nxt;

    SQL(vector<string>& names, vector<int>& columns) {
        for (int i = 0; i < (int) names.size(); ++i) {
            cols[names[i]] = columns[i];
            nxt[names[i]] = 1;
        }
    }

    bool ins(string name, vector<string> row) {
        if (!cols.count(name) || (int) row.size() != cols[name]) {
            return false;
        }
        int id = nxt[name]++;
        rows[name][id] = std::move(row);
        return true;
    }

    void rmv(string name, int rowId) {
        if (rows.count(name)) {
            rows[name].erase(rowId);
        }
    }

    string sel(string name, int rowId, int columnId) {
        if (!rows.count(name) || !rows[name].count(rowId)) {
            return "<null>";
        }
        auto& row = rows[name][rowId];
        if (columnId < 1 || columnId > (int) row.size()) {
            return "<null>";
        }
        return row[columnId - 1];
    }

    vector<string> exp(string name) {
        vector<string> ans;
        if (!rows.count(name)) {
            return ans;
        }
        for (auto& [id, row] : rows[name]) {
            string s = to_string(id);
            for (auto& cell : row) {
                s += "," + cell;
            }
            ans.push_back(s);
        }
        return ans;
    }
};

/**
 * Your SQL object will be instantiated and called as such:
 * SQL* obj = new SQL(names, columns);
 * bool param_1 = obj->ins(name,row);
 * obj->rmv(name,rowId);
 * string param_3 = obj->sel(name,rowId,columnId);
 * vector<string> param_4 = obj->exp(name);
 */

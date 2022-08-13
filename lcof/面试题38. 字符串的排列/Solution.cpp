class Solution {
public:
    void func(string str, int index, set<string>& mySet) {
        if (index == str.size()) {
            mySet.insert(str);
        } else {
            for (int i = index; i < str.size(); i++) {
                swap(str[i], str[index]);
                int temp = index + 1;
                func(str, temp, mySet);
                swap(str[i], str[index]);
            }
        }
    }

    vector<string> permutation(string s) {
        set<string> mySet;
        func(s, 0, mySet);
        vector<string> ret;
        for (string x : mySet) {
            ret.push_back(x);
        }
        return ret;
    }
};
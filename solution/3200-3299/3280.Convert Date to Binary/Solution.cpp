class Solution {
public:
    string convertDateToBinary(string date) {
        auto bin = [](string s) -> string {
            string t = bitset<32>(stoi(s)).to_string();
            return t.substr(t.find('1'));
        };
        return bin(date.substr(0, 4)) + "-" + bin(date.substr(5, 2)) + "-" + bin(date.substr(8, 2));
    }
};

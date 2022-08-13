class Solution {
public:
    string defangIPaddr(string address) {
        for (int i = address.size(); i >= 0; --i) {
            if (address[i] == '.') {
                address.replace(i, 1, "[.]");
            }
        }
        return address;
    }
};
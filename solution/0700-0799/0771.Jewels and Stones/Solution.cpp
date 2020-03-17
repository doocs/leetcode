class Solution {
public:
    int numJewelsInStones(string J, string S) {
        std::unordered_map<char,int> count;
        int number = 0;
        for(char c: J) count[c]++;
        for(char c: S){
            if(count.find(c)!=count.end())
                number++;
        }
        return number;
    }
};

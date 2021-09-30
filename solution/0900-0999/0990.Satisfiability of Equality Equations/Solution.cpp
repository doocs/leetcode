class Solution {
public:
    vector<int> p;

    bool equationsPossible(vector<string>& equations) {
        p.resize(26);
        for (int i = 0; i < 26; ++i)
            p[i] = i;
        for (auto e : equations)
        {
            int a = e[0] - 'a', b = e[3] - 'a';
            char r = e[1];
            if (r == '=')
                p[find(a)] = find(b);
        }
        for (auto e : equations)
        {
            int a = e[0] - 'a', b = e[3] - 'a';
            char r = e[1];
            if (r == '!' && find(a) == find(b))
                return false;
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};
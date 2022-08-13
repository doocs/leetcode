class Solution {
public:
    vector<int> p;

    string smallestEquivalentString(string s1, string s2, string baseStr) {
        p.resize(26);
        for (int i = 0; i < 26; ++i)
            p[i] = i;
        for (int i = 0; i < s1.size(); ++i) {
            int a = s1[i] - 'a', b = s2[i] - 'a';
            int pa = find(a), pb = find(b);
            if (pa < pb)
                p[pb] = pa;
            else
                p[pa] = pb;
        }
        string res = "";
        for (char a : baseStr) {
            char b = (char)(find(a - 'a') + 'a');
            res += b;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};
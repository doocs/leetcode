constexpr int N=1e5;
using int2=pair<int, int>;

void print(vector<int2>& a){
    for(auto [l, r]: a)
        cout<<"("<<l<<", "<<r<<")";
}

class Solution {
public:
    vector<string> maxNumOfSubstrings(string& s) {
        const int n=s.size();
        array<int2, 26> alpha;
        alpha.fill({n, -1});
        int az=0; 
        for(int i=0; i<n; i++){
            const int x=s[i]-'a';
            if (alpha[x].first==n) alpha[x].first=i, az++;
            alpha[x].second=i;
        }
        auto alpha0=alpha;

        sort(alpha.begin(), alpha.end());

        vector<int2> valid;
        valid.reserve(az);
        for(int x=0; x<az; x++){
            auto [lx, rx]=alpha[x];
            bool ok=1;

            for(int i=lx; i<=rx; i++){
                const int c=s[i]-'a';
                if (alpha0[c].first<lx) {
                    ok=0; // Char starts before lx -> lx is not a valid start
                    break;
                }
                rx=max(rx, alpha0[c].second);
            }
            if (ok) valid.emplace_back(lx, rx);
        }

        // Greedy selection by shortest right endpoint
        sort(valid.begin(), valid.end(), [](const int2& a, const int2& b){
            return a.second<b.second;});

        vector<string> ans;
        ans.reserve(valid.size());
        int lastR=-1;
        for(auto& [l, r]: valid){
            if(l>lastR){
                ans.push_back(s.substr(l, r-l+1));
                lastR=r;
            }
        }
        return ans;
    }
};

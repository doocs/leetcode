#include <bits/stdc++.h>
using namespace std;
struct Poly{
    map<vector<string>, long> d;
    Poly(long v=0){ if(v) d[{}]=v; }
    Poly(const string &s){ d[{s}]=1; }
};
Poly add(const Poly &a,const Poly &b){ Poly r=a; for(auto &p:b.d) r.d[p.first]+=p.second; for(auto it=r.d.begin();it!=r.d.end();){ if(it->second==0) r.d.erase(it++); else ++it;} return r; }
Poly sub(const Poly &a,const Poly &b){ Poly r=a; for(auto &p:b.d) r.d[p.first]-=p.second; for(auto it=r.d.begin();it!=r.d.end();){ if(it->second==0) r.d.erase(it++); else ++it;} return r; }
Poly mul(const Poly &a,const Poly &b){ Poly r; for(auto &p:a.d) for(auto &q:b.d){ auto v=p.first; v.insert(v.end(),q.first.begin(),q.first.end()); sort(v.begin(),v.end()); r.d[v]+=p.second*q.second; } for(auto it=r.d.begin();it!=r.d.end();){ if(it->second==0) r.d.erase(it++); else ++it;} return r; }
class Solution {
public:
    vector<string> basicCalculatorIV(string expr, vector<string>& evv, vector<int>& evi){
        unordered_map<string,long> mp;
        for(int i=0;i<evv.size();i++) mp[evv[i]] = evi[i];
        vector<string> toks;
        string t;
        for(char c:expr){
            if(c==' '){ if(!t.empty()){ toks.push_back(t); t.clear(); }}
            else if(strchr("()+-*",c)){
                if(!t.empty()){ toks.push_back(t); t.clear(); }
                toks.push_back(string(1,c));
            } else t.push_back(c);
        }
        if(!t.empty()) toks.push_back(t);
        int i=0;
        function<Poly()> parseE, parseT, parseP;
        parseP = [&]{ string s=toks[i++]; if(s=="("){ Poly r = parseE(); i++; return r;} if(isdigit(s[0])) return Poly(stol(s)); return mp.count(s)? Poly(mp[s]) : Poly(s); };
        parseT = [&]{ Poly r=parseP(); while(i<toks.size() && toks[i]=="*"){ i++; r = mul(r, parseP()); } return r; };
        parseE = [&]{ Poly r=parseT(); while(i<toks.size()&&(toks[i]=="+"||toks[i]=="-")){ string op=toks[i++]; Poly p=parseT(); r = (op=="+"? add(r,p) : sub(r,p)); } return r; };
        Poly res = parseE();
        vector<pair<vector<string>,long>> v(res.d.begin(), res.d.end());
        sort(v.begin(), v.end(), [](auto &a, auto &b){ if(a.first.size()!=b.first.size()) return a.first.size()>b.first.size(); return a.first<b.first; });
        vector<string> ans;
        for(auto &p:v) if(p.second){
            string s = to_string(p.second);
            for(auto &var:p.first) s += "*" + var;
            ans.push_back(s);
        }
        return ans;
    }
};
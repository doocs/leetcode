#include <vector>
#include <string>
#include <queue>
#include <stack>
#include <set>
#include <functional>
using namespace std;

class Solution
{
public:
    vector<vector<int>> supersequences(vector<string> &words)
    {
        const int ALPHA = 26;
        bool used[ALPHA] = {};
        for (auto &w : words)
            for (char c : w)
                used[c - 'a'] = true;

        vector<int> charMap(ALPHA, -1);
        vector<char> chars;
        int charCount = 0;
        for (int c = 0; c < ALPHA; c++)
        {
            if (used[c])
            {
                charMap[c] = charCount++;
                chars.push_back('a' + c);
            }
        }

        vector<vector<bool>> graph(charCount, vector<bool>(charCount));
        vector<bool> selfLoop(charCount);
        for (auto &w : words)
        {
            int u = charMap[w[0] - 'a'], v = charMap[w[1] - 'a'];
            if (u == v)
                selfLoop[u] = true;
            else
                graph[u][v] = true;
        }

        vector<int> disc(charCount, -1), low(charCount), comp(charCount, -1);
        vector<bool> inStack(charCount);
        stack<int> stk;
        int time = 0, sccTotal = 0;

        function<void(int)> tarjan = [&](int u)
        {
            disc[u] = low[u] = time++;
            stk.push(u);
            inStack[u] = true;

            for (int v = 0; v < charCount; v++)
            {
                if (!graph[u][v])
                    continue;
                if (disc[v] == -1)
                {
                    tarjan(v);
                    low[u] = min(low[u], low[v]);
                }
                else if (inStack[v])
                {
                    low[u] = min(low[u], disc[v]);
                }
            }

            if (low[u] == disc[u])
            {
                while (true)
                {
                    int v = stk.top();
                    stk.pop();
                    inStack[v] = false;
                    comp[v] = sccTotal;
                    if (v == u)
                        break;
                }
                sccTotal++;
            }
        };

        for (int i = 0; i < charCount; i++)
            if (disc[i] == -1)
                tarjan(i);

        vector<vector<int>> sccGroups(sccTotal);
        for (int i = 0; i < charCount; i++)
            sccGroups[comp[i]].push_back(i);

        vector<vector<int>> sccGraph(sccTotal);
        vector<int> inDegree(sccTotal);
        for (int u = 0; u < charCount; u++)
        {
            for (int v = 0; v < charCount; v++)
            {
                if (graph[u][v] && comp[u] != comp[v])
                {
                    sccGraph[comp[u]].push_back(comp[v]);
                    inDegree[comp[v]]++;
                }
            }
        }

        queue<int> q;
        vector<int> topoOrder;
        for (int i = 0; i < sccTotal; i++)
            if (inDegree[i] == 0)
                q.push(i);

        while (!q.empty())
        {
            int u = q.front();
            q.pop();
            topoOrder.push_back(u);
            for (int v : sccGraph[u])
            {
                if (--inDegree[v] == 0)
                    q.push(v);
            }
        }

        auto isAcyclic = [](const vector<vector<bool>> &g, int mask, int n)
        {
            vector<bool> removed(n);
            for (int i = 0; i < n; i++)
                if (mask & (1 << i))
                    removed[i] = true;

            vector<int> deg(n);
            for (int u = 0; u < n; u++)
            {
                if (removed[u])
                    continue;
                for (int v = 0; v < n; v++)
                {
                    if (!removed[v] && g[u][v])
                        deg[v]++;
                }
            }

            queue<int> q;
            int cnt = 0;
            int total = n - __builtin_popcount(mask);
            for (int i = 0; i < n; i++)
                if (!removed[i] && deg[i] == 0)
                    q.push(i);

            while (!q.empty())
            {
                int u = q.front();
                q.pop();
                cnt++;
                for (int v = 0; v < n; v++)
                {
                    if (!removed[v] && g[u][v] && --deg[v] == 0)
                        q.push(v);
                }
            }
            return cnt == total;
        };

        auto findMinFVS = [&](const vector<vector<bool>> &g, int n)
        {
            set<vector<int>> patterns;
            for (int sz = 0; sz <= n; sz++)
            {
                bool found = false;
                for (int mask = 0; mask < (1 << n); mask++)
                {
                    if (__builtin_popcount(mask) != sz)
                        continue;
                    if (isAcyclic(g, mask, n))
                    {
                        vector<int> freq(n, 1);
                        for (int i = 0; i < n; i++)
                            if (mask & (1 << i))
                                freq[i] = 2;
                        patterns.insert(freq);
                        found = true;
                    }
                }
                if (found)
                    break;
            }
            return vector<vector<int>>(patterns.begin(), patterns.end());
        };

        vector<vector<vector<int>>> sccPatterns(sccTotal);
        for (int i = 0; i < sccTotal; i++)
        {
            auto &group = sccGroups[i];
            if (group.size() == 1)
            {
                sccPatterns[i] = selfLoop[group[0]] ? vector<vector<int>>{{2}} : vector<vector<int>>{{1}};
                continue;
            }

            vector<vector<bool>> subgraph(group.size(), vector<bool>(group.size()));
            vector<int> localToGlobal(group.size());
            for (int j = 0; j < group.size(); j++)
            {
                localToGlobal[j] = group[j];
                if (selfLoop[group[j]])
                    subgraph[j][j] = true;
                for (int k = 0; k < group.size(); k++)
                {
                    if (graph[group[j]][group[k]])
                        subgraph[j][k] = true;
                }
            }
            sccPatterns[i] = findMinFVS(subgraph, group.size());
        }

        vector<vector<int>> result = {{}};
        for (int scc : topoOrder)
        {
            vector<vector<int>> newResult;
            for (auto &freq : result)
            {
                for (auto &pattern : sccPatterns[scc])
                {
                    vector<int> newFreq = freq;
                    newFreq.resize(charCount);
                    for (int i = 0; i < sccGroups[scc].size(); i++)
                    {
                        int globalIdx = sccGroups[scc][i];
                        newFreq[globalIdx] = pattern[i];
                    }
                    newResult.push_back(newFreq);
                }
            }
            result = move(newResult);
        }

        set<vector<int>> uniqueFreqs;
        for (auto &freq : result)
        {
            vector<int> finalFreq(ALPHA);
            for (int i = 0; i < charCount; i++)
                finalFreq[chars[i] - 'a'] = freq[i];
            uniqueFreqs.insert(finalFreq);
        }

        return vector<vector<int>>(uniqueFreqs.begin(), uniqueFreqs.end());
    }
};
---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0721.Accounts%20Merge/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Hash Table
    - String
    - Sorting
---

<!-- problem:start -->

# [721. Accounts Merge](https://leetcode.com/problems/accounts-merge)

[中文文档](/solution/0700-0799/0721.Accounts%20Merge/README.md)

## Description

<!-- description:start -->

<p>Given a list of <code>accounts</code> where each element <code>accounts[i]</code> is a list of strings, where the first element <code>accounts[i][0]</code> is a name, and the rest of the elements are <strong>emails</strong> representing emails of the account.</p>

<p>Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.</p>

<p>After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails <strong>in sorted order</strong>. The accounts themselves can be returned in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[&quot;John&quot;,&quot;johnsmith@mail.com&quot;,&quot;john_newyork@mail.com&quot;],[&quot;John&quot;,&quot;johnsmith@mail.com&quot;,&quot;john00@mail.com&quot;],[&quot;Mary&quot;,&quot;mary@mail.com&quot;],[&quot;John&quot;,&quot;johnnybravo@mail.com&quot;]]
<strong>Output:</strong> [[&quot;John&quot;,&quot;john00@mail.com&quot;,&quot;john_newyork@mail.com&quot;,&quot;johnsmith@mail.com&quot;],[&quot;Mary&quot;,&quot;mary@mail.com&quot;],[&quot;John&quot;,&quot;johnnybravo@mail.com&quot;]]
<strong>Explanation:</strong>
The first and second John&#39;s are the same person as they have the common email &quot;johnsmith@mail.com&quot;.
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [[&#39;Mary&#39;, &#39;mary@mail.com&#39;], [&#39;John&#39;, &#39;johnnybravo@mail.com&#39;], 
[&#39;John&#39;, &#39;john00@mail.com&#39;, &#39;john_newyork@mail.com&#39;, &#39;johnsmith@mail.com&#39;]] would still be accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[&quot;Gabe&quot;,&quot;Gabe0@m.co&quot;,&quot;Gabe3@m.co&quot;,&quot;Gabe1@m.co&quot;],[&quot;Kevin&quot;,&quot;Kevin3@m.co&quot;,&quot;Kevin5@m.co&quot;,&quot;Kevin0@m.co&quot;],[&quot;Ethan&quot;,&quot;Ethan5@m.co&quot;,&quot;Ethan4@m.co&quot;,&quot;Ethan0@m.co&quot;],[&quot;Hanzo&quot;,&quot;Hanzo3@m.co&quot;,&quot;Hanzo1@m.co&quot;,&quot;Hanzo0@m.co&quot;],[&quot;Fern&quot;,&quot;Fern5@m.co&quot;,&quot;Fern1@m.co&quot;,&quot;Fern0@m.co&quot;]]
<strong>Output:</strong> [[&quot;Ethan&quot;,&quot;Ethan0@m.co&quot;,&quot;Ethan4@m.co&quot;,&quot;Ethan5@m.co&quot;],[&quot;Gabe&quot;,&quot;Gabe0@m.co&quot;,&quot;Gabe1@m.co&quot;,&quot;Gabe3@m.co&quot;],[&quot;Hanzo&quot;,&quot;Hanzo0@m.co&quot;,&quot;Hanzo1@m.co&quot;,&quot;Hanzo3@m.co&quot;],[&quot;Kevin&quot;,&quot;Kevin0@m.co&quot;,&quot;Kevin3@m.co&quot;,&quot;Kevin5@m.co&quot;],[&quot;Fern&quot;,&quot;Fern0@m.co&quot;,&quot;Fern1@m.co&quot;,&quot;Fern5@m.co&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= accounts.length &lt;= 1000</code></li>
	<li><code>2 &lt;= accounts[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= accounts[i][j].length &lt;= 30</code></li>
	<li><code>accounts[i][0]</code> consists of English letters.</li>
	<li><code>accounts[i][j] (for j &gt; 0)</code> is a valid email.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find + Hash Table

Based on the problem description, we can use a union-find data structure to merge accounts with the same email address. The specific steps are as follows:

First, we iterate through all the accounts. For the $i$th account, we iterate through all its email addresses. If an email address appears in the hash table $\textit{d}$, we use the union-find to merge the account's index $i$ with the previously appeared account's index; otherwise, we map this email address to the account's index $i$.

Next, we iterate through all the accounts again. For the $i$th account, we use the union-find to find its root node, and then add all the email addresses of that account to the hash table $\textit{g}$, where the key is the root node, and the value is the account's email addresses.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of accounts.

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        uf = UnionFind(len(accounts))
        d = {}
        for i, (_, *emails) in enumerate(accounts):
            for email in emails:
                if email in d:
                    uf.union(i, d[email])
                else:
                    d[email] = i
        g = defaultdict(set)
        for i, (_, *emails) in enumerate(accounts):
            root = uf.find(i)
            g[root].update(emails)
        return [[accounts[root][0]] + sorted(emails) for root, emails in g.items()]
```

#### Java

```java
class UnionFind {
    private final int[] p;
    private final int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);
        Map<String, Integer> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < accounts.get(i).size(); ++j) {
                String email = accounts.get(i).get(j);
                if (d.containsKey(email)) {
                    uf.union(i, d.get(email));
                } else {
                    d.put(email, i);
                }
            }
        }
        Map<Integer, Set<String>> g = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int root = uf.find(i);
            g.computeIfAbsent(root, k -> new HashSet<>())
                .addAll(accounts.get(i).subList(1, accounts.get(i).size()));
        }
        List<List<String>> ans = new ArrayList<>();
        for (var e : g.entrySet()) {
            List<String> emails = new ArrayList<>(e.getValue());
            Collections.sort(emails);
            ans.add(new ArrayList<>());
            ans.get(ans.size() - 1).add(accounts.get(e.getKey()).get(0));
            ans.get(ans.size() - 1).addAll(emails);
        }
        return ans;
    }
}
```

#### C++

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        int n = accounts.size();
        UnionFind uf(n);
        unordered_map<string, int> d;
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < accounts[i].size(); ++j) {
                const string& email = accounts[i][j];
                if (d.find(email) != d.end()) {
                    uf.unite(i, d[email]);
                } else {
                    d[email] = i;
                }
            }
        }
        unordered_map<int, set<string>> g;
        for (int i = 0; i < n; ++i) {
            int root = uf.find(i);
            g[root].insert(accounts[i].begin() + 1, accounts[i].end());
        }
        vector<vector<string>> ans;
        for (const auto& [root, s] : g) {
            vector<string> emails(s.begin(), s.end());
            emails.insert(emails.begin(), accounts[root][0]);
            ans.push_back(emails);
        }
        return ans;
    }
};
```

#### Go

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func accountsMerge(accounts [][]string) (ans [][]string) {
	n := len(accounts)
	uf := newUnionFind(n)
	d := make(map[string]int)
	for i := 0; i < n; i++ {
		for _, email := range accounts[i][1:] {
			if j, ok := d[email]; ok {
				uf.union(i, j)
			} else {
				d[email] = i
			}
		}
	}
	g := make(map[int]map[string]struct{})
	for i := 0; i < n; i++ {
		root := uf.find(i)
		if _, ok := g[root]; !ok {
			g[root] = make(map[string]struct{})
		}
		for _, email := range accounts[i][1:] {
			g[root][email] = struct{}{}
		}
	}
	for root, s := range g {
		emails := []string{}
		for email := range s {
			emails = append(emails, email)
		}
		sort.Strings(emails)
		account := append([]string{accounts[root][0]}, emails...)
		ans = append(ans, account)
	}
	return
}
```

#### TypeScript

```ts
class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = new Array(n);
        this.size = new Array(n);
        for (let i = 0; i < n; ++i) {
            this.p[i] = i;
            this.size[i] = 1;
        }
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): boolean {
        let pa = this.find(a),
            pb = this.find(b);
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }
}

function accountsMerge(accounts: string[][]): string[][] {
    const n = accounts.length;
    const uf = new UnionFind(n);
    const d = new Map<string, number>();

    for (let i = 0; i < n; ++i) {
        for (let j = 1; j < accounts[i].length; ++j) {
            const email = accounts[i][j];
            if (d.has(email)) {
                uf.union(i, d.get(email)!);
            } else {
                d.set(email, i);
            }
        }
    }

    const g = new Map<number, Set<string>>();
    for (let i = 0; i < n; ++i) {
        const root = uf.find(i);
        if (!g.has(root)) {
            g.set(root, new Set<string>());
        }
        const emailSet = g.get(root)!;
        for (let j = 1; j < accounts[i].length; ++j) {
            emailSet.add(accounts[i][j]);
        }
    }

    const ans: string[][] = [];
    for (const [root, emails] of g.entries()) {
        const emailList = Array.from(emails).sort();
        const mergedAccount = [accounts[root][0], ...emailList];
        ans.push(mergedAccount);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

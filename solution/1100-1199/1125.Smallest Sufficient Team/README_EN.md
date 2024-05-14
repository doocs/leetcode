# [1125. Smallest Sufficient Team](https://leetcode.com/problems/smallest-sufficient-team)

[中文文档](/solution/1100-1199/1125.Smallest%20Sufficient%20Team/README.md)

<!-- tags:Bit Manipulation,Array,Dynamic Programming,Bitmask -->

<!-- difficulty:Hard -->

## Description

<p>In a project, you have a list of required skills <code>req_skills</code>, and a list of people. The <code>i<sup>th</sup></code> person <code>people[i]</code> contains a list of skills that the person has.</p>

<p>Consider a sufficient team: a set of people such that for every required skill in <code>req_skills</code>, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.</p>

<ul>
	<li>For example, <code>team = [0, 1, 3]</code> represents the people with skills <code>people[0]</code>, <code>people[1]</code>, and <code>people[3]</code>.</li>
</ul>

<p>Return <em>any sufficient team of the smallest possible size, represented by the index of each person</em>. You may return the answer in <strong>any order</strong>.</p>

<p>It is <strong>guaranteed</strong> an answer exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
<strong>Output:</strong> [0,2]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
<strong>Output:</strong> [1,2]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= req_skills.length &lt;= 16</code></li>
	<li><code>1 &lt;= req_skills[i].length &lt;= 16</code></li>
	<li><code>req_skills[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>req_skills</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= people.length &lt;= 60</code></li>
	<li><code>0 &lt;= people[i].length &lt;= 16</code></li>
	<li><code>1 &lt;= people[i][j].length &lt;= 16</code></li>
	<li><code>people[i][j]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>people[i]</code> are <strong>unique</strong>.</li>
	<li>Every skill in <code>people[i]</code> is a skill in <code>req_skills</code>.</li>
	<li>It is guaranteed a sufficient team exists.</li>
</ul>

## Solutions

### Solution 1: State Compression Dynamic Programming

We notice that the length of `req_skills` does not exceed $16$, so we can use a binary number of length no more than $16$ to represent whether each skill is mastered. Let's denote the length of `req_skills` as $m$ and the length of `people` as $n$.

First, we map each skill in `req_skills` to a number, i.e., $d[s]$ represents the number of skill $s$. Then, we traverse each person in `people` and represent the skills they master with a binary number, i.e., $p[i]$ represents the skills mastered by the person with number $i$.

Next, we define the following three arrays:

-   Array $f[i]$ represents the minimum number of people to master the skill set $i$, where each bit of the binary representation of $i$ is $1$, indicating that the corresponding skill is mastered. Initially, $f[0] = 0$, and all other positions are infinity.
-   Array $g[i]$ represents the number of the last person when the skill set $i$ is mastered by the minimum number of people.
-   Array $h[i]$ represents the previous skill set state when the skill set $i$ is mastered by the minimum number of people.

We enumerate each skill set in the range of $[0,..2^m-1]$, for each skill set $i$:

We enumerate each person $j$ in `people`. If $f[i] + 1 \lt f[i | p[j]]$, it means that $f[i | p[j]]$ can be transferred from $f[i]$. At this time, we update $f[i | p[j]]$ to $f[i] + 1$, and update $g[i | p[j]]$ to $j$, and update $h[i | p[j]]$ to $i$. That is, when the current skill set state is $i | p[j]$, the number of the last person is $j$, and the previous skill set state is $i$. Here, the symbol $|$ represents bitwise OR operation.

Finally, we start from the skill set $i=2^m-1$, find the number of the last person at this time $g[i]$, add it to the answer, then update $i$ to $h[i]$, and keep backtracking until $i=0$, to get the personnel numbers in the smallest necessary team.

The time complexity is $O(2^m \times n)$, and the space complexity is $O(2^m)$. Here, $m$ and $n$ are the lengths of `req_skills` and `people`, respectively.

<!-- tabs:start -->

```python
class Solution:
    def smallestSufficientTeam(
        self, req_skills: List[str], people: List[List[str]]
    ) -> List[int]:
        d = {s: i for i, s in enumerate(req_skills)}
        m, n = len(req_skills), len(people)
        p = [0] * n
        for i, ss in enumerate(people):
            for s in ss:
                p[i] |= 1 << d[s]
        f = [inf] * (1 << m)
        g = [0] * (1 << m)
        h = [0] * (1 << m)
        f[0] = 0
        for i in range(1 << m):
            if f[i] == inf:
                continue
            for j in range(n):
                if f[i] + 1 < f[i | p[j]]:
                    f[i | p[j]] = f[i] + 1
                    g[i | p[j]] = j
                    h[i | p[j]] = i
        i = (1 << m) - 1
        ans = []
        while i:
            ans.append(g[i])
            i = h[i]
        return ans
```

```java
class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> d = new HashMap<>();
        int m = req_skills.length;
        int n = people.size();
        for (int i = 0; i < m; ++i) {
            d.put(req_skills[i], i);
        }
        int[] p = new int[n];
        for (int i = 0; i < n; ++i) {
            for (var s : people.get(i)) {
                p[i] |= 1 << d.get(s);
            }
        }
        int[] f = new int[1 << m];
        int[] g = new int[1 << m];
        int[] h = new int[1 << m];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 0; i < 1 << m; ++i) {
            if (f[i] == inf) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (f[i] + 1 < f[i | p[j]]) {
                    f[i | p[j]] = f[i] + 1;
                    g[i | p[j]] = j;
                    h[i | p[j]] = i;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = (1 << m) - 1; i != 0; i = h[i]) {
            ans.add(g[i]);
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

```cpp
class Solution {
public:
    vector<int> smallestSufficientTeam(vector<string>& req_skills, vector<vector<string>>& people) {
        unordered_map<string, int> d;
        int m = req_skills.size(), n = people.size();
        for (int i = 0; i < m; ++i) {
            d[req_skills[i]] = i;
        }
        int p[n];
        memset(p, 0, sizeof(p));
        for (int i = 0; i < n; ++i) {
            for (auto& s : people[i]) {
                p[i] |= 1 << d[s];
            }
        }
        int f[1 << m];
        int g[1 << m];
        int h[1 << m];
        memset(f, 63, sizeof(f));
        f[0] = 0;
        for (int i = 0; i < 1 << m; ++i) {
            if (f[i] == 0x3f3f3f3f) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (f[i] + 1 < f[i | p[j]]) {
                    f[i | p[j]] = f[i] + 1;
                    g[i | p[j]] = j;
                    h[i | p[j]] = i;
                }
            }
        }
        vector<int> ans;
        for (int i = (1 << m) - 1; i; i = h[i]) {
            ans.push_back(g[i]);
        }
        return ans;
    }
};
```

```go
func smallestSufficientTeam(req_skills []string, people [][]string) (ans []int) {
	d := map[string]int{}
	for i, s := range req_skills {
		d[s] = i
	}
	m, n := len(req_skills), len(people)
	p := make([]int, n)
	for i, ss := range people {
		for _, s := range ss {
			p[i] |= 1 << d[s]
		}
	}
	const inf = 1 << 30
	f := make([]int, 1<<m)
	g := make([]int, 1<<m)
	h := make([]int, 1<<m)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0
	for i := range f {
		if f[i] == inf {
			continue
		}
		for j := 0; j < n; j++ {
			if f[i]+1 < f[i|p[j]] {
				f[i|p[j]] = f[i] + 1
				g[i|p[j]] = j
				h[i|p[j]] = i
			}
		}
	}
	for i := 1<<m - 1; i != 0; i = h[i] {
		ans = append(ans, g[i])
	}
	return
}
```

```ts
function smallestSufficientTeam(req_skills: string[], people: string[][]): number[] {
    const d: Map<string, number> = new Map();
    const m = req_skills.length;
    const n = people.length;
    for (let i = 0; i < m; ++i) {
        d.set(req_skills[i], i);
    }
    const p: number[] = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (const s of people[i]) {
            p[i] |= 1 << d.get(s)!;
        }
    }
    const inf = 1 << 30;
    const f: number[] = new Array(1 << m).fill(inf);
    const g: number[] = new Array(1 << m).fill(0);
    const h: number[] = new Array(1 << m).fill(0);
    f[0] = 0;
    for (let i = 0; i < 1 << m; ++i) {
        if (f[i] === inf) {
            continue;
        }
        for (let j = 0; j < n; ++j) {
            if (f[i] + 1 < f[i | p[j]]) {
                f[i | p[j]] = f[i] + 1;
                g[i | p[j]] = j;
                h[i | p[j]] = i;
            }
        }
    }
    const ans: number[] = [];
    for (let i = (1 << m) - 1; i; i = h[i]) {
        ans.push(g[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->

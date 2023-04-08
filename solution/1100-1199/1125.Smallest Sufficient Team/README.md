# [1125. 最小的必要团队](https://leetcode.cn/problems/smallest-sufficient-team)

[English Version](/solution/1100-1199/1125.Smallest%20Sufficient%20Team/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>作为项目经理，你规划了一份需求的技能清单 <code>req_skills</code>，并打算从备选人员名单 <code>people</code> 中选出些人组成一个「必要团队」（ 编号为 <code>i</code> 的备选人员 <code>people[i]</code> 含有一份该备选人员掌握的技能列表）。</p>

<p>所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 <code>req_skills</code> 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：</p>

<ul>
	<li>例如，团队 <code>team = [0, 1, 3]</code> 表示掌握技能分别为 <code>people[0]</code>，<code>people[1]</code>，和 <code>people[3]</code> 的备选人员。</li>
</ul>

<p>请你返回 <strong>任一</strong> 规模最小的必要团队，团队成员用人员编号表示。你可以按 <strong>任意顺序</strong> 返回答案，题目数据保证答案存在。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
<strong>输出：</strong>[0,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
<strong>输出：</strong>[1,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= req_skills.length <= 16</code></li>
	<li><code>1 <= req_skills[i].length <= 16</code></li>
	<li><code>req_skills[i]</code> 由小写英文字母组成</li>
	<li><code>req_skills</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li><code>1 <= people.length <= 60</code></li>
	<li><code>0 <= people[i].length <= 16</code></li>
	<li><code>1 <= people[i][j].length <= 16</code></li>
	<li><code>people[i][j]</code> 由小写英文字母组成</li>
	<li><code>people[i]</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li><code>people[i]</code> 中的每个技能是 <code>req_skills</code> 中的技能</li>
	<li>题目数据保证「必要团队」一定存在</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩动态规划**

我们注意到，技能清单 `req_skills` 的长度不超过 $16$，因此，我们可以用一个长度不超过 $16$ 的二进制数来表示每一种技能是否被掌握。不妨记数组 `req_skills` 的长度为 $m$，数组 `people` 的长度为 $n$。

我们先将 `req_skills` 中的每个技能映射到一个编号，即 $d[s]$ 表示技能 $s$ 的编号。然后，我们遍历 `people` 中的每个人，将其掌握的技能用二进制数表示，即 $p[i]$ 表示编号为 $i$ 的人掌握的技能。

接下来，我们定义以下三个数组，其中：

-   数组 $f[i]$ 表示掌握技能集合为 $i$ 的最少人数，其中 $i$ 的二进制表示中的每一位为 $1$ 的位置，表示对应的技能被掌握。初始时 $f[0] = 0$，其余位置均为无穷大。
-   数组 $g[i]$ 表示掌握技能集合为 $i$ 的最少人数时，最后一个人的编号。
-   数组 $h[i]$ 表示掌握技能集合为 $i$ 的最少人数时，上一个技能集合状态。

我们在 $[0,..2^m-1]$ 的范围内枚举每个技能集合，对于每个技能集合 $i$：

我们枚举 `people` 中的每个人 $j$，如果 $f[i] + 1 \lt f[i | p[j]]$，说明 $f[i | p[j]]$ 可以通过 $f[i]$ 转移得到，此时，我们更新 $f[i | p[j]]$ 为 $f[i] + 1$，并将 $g[i | p[j]]$ 更新为 $j$，同时将 $h[i | p[j]]$ 更新为 $i$。即当前技能集合状态为 $i | p[j]$ 时，最后一个人的编号为 $j$，上一个技能集合状态为 $i$。这里符号 $|$ 表示按位或运算。

最后，我们从技能集合 $i=2^m-1$ 开始，找到此时最后一个人的编号 $g[i]$，将其加入答案中，然后将 $i$ 更新为 $h[i]$，不断地向前回溯，直到 $i=0$，即可得到最小的必要团队中的人员编号。

时间复杂度 $O(2^m \times n)$，空间复杂度 $O(2^m)$。其中 $m$ 和 $n$ 分别为 `req_skills` 和 `people` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function smallestSufficientTeam(
    req_skills: string[],
    people: string[][],
): number[] {
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

### **...**

```

```

<!-- tabs:end -->

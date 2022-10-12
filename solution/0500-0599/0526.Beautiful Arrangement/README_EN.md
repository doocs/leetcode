# [526. Beautiful Arrangement](https://leetcode.com/problems/beautiful-arrangement)

[中文文档](/solution/0500-0599/0526.Beautiful%20Arrangement/README.md)

## Description

<p>Suppose you have <code>n</code> integers labeled <code>1</code> through <code>n</code>. A permutation of those <code>n</code> integers <code>perm</code> (<strong>1-indexed</strong>) is considered a <strong>beautiful arrangement</strong> if for every <code>i</code> (<code>1 &lt;= i &lt;= n</code>), <strong>either</strong> of the following is true:</p>

<ul>
	<li><code>perm[i]</code> is divisible by <code>i</code>.</li>
	<li><code>i</code> is divisible by <code>perm[i]</code>.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the <strong>number</strong> of the <strong>beautiful arrangements</strong> that you can construct</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 2
<b>Explanation:</b> 
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countArrangement(self, n: int) -> int:
        def dfs(i):
            nonlocal ans, n
            if i == n + 1:
                ans += 1
                return
            for j in match[i]:
                if not vis[j]:
                    vis[j] = True
                    dfs(i + 1)
                    vis[j] = False

        ans = 0
        vis = [False] * (n + 1)
        match = defaultdict(list)
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if j % i == 0 or i % j == 0:
                    match[i].append(j)

        dfs(1)
        return ans
```

### **Java**

```java
class Solution {
    private int n;
    private int ans;
    private boolean[] vis;
    private Map<Integer, List<Integer>> match;

    public int countArrangement(int n) {
        this.n = n;
        ans = 0;
        vis = new boolean[n + 1];
        match = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i % j == 0 || j % i == 0) {
                    match.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (i == n + 1) {
            ++ans;
            return;
        }
        if (!match.containsKey(i)) {
            return;
        }
        for (int j : match.get(i)) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
}
```

```java
class Solution {
    public int countArrangement(int N) {
        int maxn = 1 << N;
        int[] f = new int[maxn];
        f[0] = 1;
        for (int i = 0; i < maxn; ++i) {
            int s = 1;
            for (int j = 0; j < N; ++j) {
                s += (i >> j) & 1;
            }
            for (int j = 1; j <= N; ++j) {
                if (((i >> (j - 1) & 1) == 0) && (s % j == 0 || j % s == 0)) {
                    f[i | (1 << (j - 1))] += f[i];
                }
            }
        }
        return f[maxn - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int n;
    int ans;
    vector<bool> vis;
    unordered_map<int, vector<int>> match;

    int countArrangement(int n) {
        this->n = n;
        this->ans = 0;
        vis.resize(n + 1);
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j)
                if (i % j == 0 || j % i == 0)
                    match[i].push_back(j);
        dfs(1);
        return ans;
    }

    void dfs(int i) {
        if (i == n + 1) {
            ++ans;
            return;
        }
        for (int j : match[i]) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1);
                vis[j] = false;
            }
        }
    }
};
```

### **Go**

```go
func countArrangement(n int) int {
	ans := 0
	match := make(map[int][]int)
	for i := 1; i <= n; i++ {
		for j := 1; j <= n; j++ {
			if i%j == 0 || j%i == 0 {
				match[i] = append(match[i], j)
			}
		}
	}
	vis := make([]bool, n+1)

	var dfs func(i int)
	dfs = func(i int) {
		if i == n+1 {
			ans++
			return
		}
		for _, j := range match[i] {
			if !vis[j] {
				vis[j] = true
				dfs(i + 1)
				vis[j] = false
			}
		}
	}

	dfs(1)
	return ans
}
```

### **TypeScript**

```ts
function countArrangement(n: number): number {
    const vis = new Array(n + 1).fill(0);
    const match = Array.from({ length: n + 1 }, () => []);
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= n; j++) {
            if (i % j === 0 || j % i === 0) {
                match[i].push(j);
            }
        }
    }

    let res = 0;
    const dfs = (i: number, n: number) => {
        if (i === n + 1) {
            res++;
            return;
        }
        for (const j of match[i]) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, n);
                vis[j] = false;
            }
        }
    };
    dfs(1, n);
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, n: usize, mat: &Vec<Vec<usize>>, vis: &mut Vec<bool>, res: &mut i32) {
        if i == n + 1 {
            *res += 1;
            return;
        }
        for &j in mat[i].iter() {
            if !vis[j] {
                vis[j] = true;
                Self::dfs(i + 1, n, mat, vis, res);
                vis[j] = false;
            }
        }
    }

    pub fn count_arrangement(n: i32) -> i32 {
        let n = n as usize;
        let mut vis = vec![false; n + 1];
        let mut mat = vec![Vec::new(); n + 1];
        for i in 1..=n {
            for j in 1..=n {
                if i % j == 0 || j % i == 0 {
                    mat[i].push(j);
                }
            }
        }

        let mut res = 0;
        Self::dfs(1, n, &mat, &mut vis, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->

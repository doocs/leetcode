---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1707.Maximum%20XOR%20With%20an%20Element%20From%20Array/README_EN.md
rating: 2358
tags:
    - Bit Manipulation
    - Trie
    - Array
---

# [1707. Maximum XOR With an Element From Array](https://leetcode.com/problems/maximum-xor-with-an-element-from-array)

[中文文档](/solution/1700-1799/1707.Maximum%20XOR%20With%20an%20Element%20From%20Array/README.md)

## Description

<p>You are given an array <code>nums</code> consisting of non-negative integers. You are also given a <code>queries</code> array, where <code>queries[i] = [x<sub>i</sub>, m<sub>i</sub>]</code>.</p>

<p>The answer to the <code>i<sup>th</sup></code> query is the maximum bitwise <code>XOR</code> value of <code>x<sub>i</sub></code> and any element of <code>nums</code> that does not exceed <code>m<sub>i</sub></code>. In other words, the answer is <code>max(nums[j] XOR x<sub>i</sub>)</code> for all <code>j</code> such that <code>nums[j] &lt;= m<sub>i</sub></code>. If all elements in <code>nums</code> are larger than <code>m<sub>i</sub></code>, then the answer is <code>-1</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer.length == queries.length</code><em> and </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
<strong>Output:</strong> [3,3,7]
<strong>Explanation:</strong>
1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
<strong>Output:</strong> [15,-1,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= nums[j], x<sub>i</sub>, m<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Offline Query + Binary Trie

From the problem description, we know that each query is independent and the result of the query is irrelevant to the order of elements in $nums$. Therefore, we consider sorting all queries in ascending order of $m_i$, and also sorting $nums$ in ascending order.

Next, we use a binary trie to maintain the elements in $nums$. We use a pointer $j$ to record the current elements in the trie, initially $j=0$. For each query $[x_i, m_i]$, we continuously insert elements from $nums$ into the trie until $nums[j] > m_i$. At this point, we can query all elements not exceeding $m_i$ in the trie, and we take the XOR value of the element with the maximum XOR value with $x_i$ as the answer.

The time complexity is $O(m \times \log m + n \times (\log n + \log M))$, and the space complexity is $O(n \times \log M)$. Where $m$ and $n$ are the lengths of the arrays $nums$ and $queries$ respectively, and $M$ is the maximum value in the array $nums$. In this problem, $M \le 10^9$.

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ["children"]

    def __init__(self):
        self.children = [None] * 2

    def insert(self, x: int):
        node = self
        for i in range(30, -1, -1):
            v = x >> i & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]

    def search(self, x: int) -> int:
        node = self
        ans = 0
        for i in range(30, -1, -1):
            v = x >> i & 1
            if node.children[v ^ 1]:
                ans |= 1 << i
                node = node.children[v ^ 1]
            elif node.children[v]:
                node = node.children[v]
            else:
                return -1
        return ans


class Solution:
    def maximizeXor(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        trie = Trie()
        nums.sort()
        j, n = 0, len(queries)
        ans = [-1] * n
        for i, (x, m) in sorted(zip(range(n), queries), key=lambda x: x[1][1]):
            while j < len(nums) and nums[j] <= m:
                trie.insert(nums[j])
                j += 1
            ans[i] = trie.search(x)
        return ans
```

```java
class Trie {
    private Trie[] children = new Trie[2];

    public void insert(int x) {
        Trie node = this;
        for (int i = 30; i >= 0; --i) {
            int v = x >> i & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
        }
    }

    public int search(int x) {
        Trie node = this;
        int ans = 0;
        for (int i = 30; i >= 0; --i) {
            int v = x >> i & 1;
            if (node.children[v ^ 1] != null) {
                ans |= 1 << i;
                node = node.children[v ^ 1];
            } else if (node.children[v] != null) {
                node = node.children[v];
            } else {
                return -1;
            }
        }
        return ans;
    }
}

class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int n = queries.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i][1] - queries[j][1]);
        int[] ans = new int[n];
        Trie trie = new Trie();
        int j = 0;
        for (int i : idx) {
            int x = queries[i][0], m = queries[i][1];
            while (j < nums.length && nums[j] <= m) {
                trie.insert(nums[j++]);
            }
            ans[i] = trie.search(x);
        }
        return ans;
    }
}
```

```cpp
class Trie {
private:
    Trie* children[2];

public:
    Trie()
        : children{nullptr, nullptr} {}

    void insert(int x) {
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (!node->children[v]) {
                node->children[v] = new Trie();
            }
            node = node->children[v];
        }
    }

    int search(int x) {
        Trie* node = this;
        int ans = 0;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                ans |= 1 << i;
                node = node->children[v ^ 1];
            } else if (node->children[v]) {
                node = node->children[v];
            } else {
                return -1;
            }
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> maximizeXor(vector<int>& nums, vector<vector<int>>& queries) {
        sort(nums.begin(), nums.end());
        int n = queries.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return queries[i][1] < queries[j][1]; });
        vector<int> ans(n);
        Trie trie;
        int j = 0;
        for (int i : idx) {
            int x = queries[i][0], m = queries[i][1];
            while (j < nums.size() && nums[j] <= m) {
                trie.insert(nums[j++]);
            }
            ans[i] = trie.search(x);
        }
        return ans;
    }
};
```

```go
type Trie struct {
	children [2]*Trie
}

func NewTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(x int) {
	node := t
	for i := 30; i >= 0; i-- {
		v := x >> i & 1
		if node.children[v] == nil {
			node.children[v] = NewTrie()
		}
		node = node.children[v]
	}
}

func (t *Trie) search(x int) int {
	node := t
	ans := 0
	for i := 30; i >= 0; i-- {
		v := x >> i & 1
		if node.children[v^1] != nil {
			ans |= 1 << i
			node = node.children[v^1]
		} else if node.children[v] != nil {
			node = node.children[v]
		} else {
			return -1
		}
	}
	return ans
}

func maximizeXor(nums []int, queries [][]int) []int {
	sort.Ints(nums)
	n := len(queries)
	idx := make([]int, n)
	for i := 0; i < n; i++ {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool {
		return queries[idx[i]][1] < queries[idx[j]][1]
	})
	ans := make([]int, n)
	trie := NewTrie()
	j := 0
	for _, i := range idx {
		x, m := queries[i][0], queries[i][1]
		for j < len(nums) && nums[j] <= m {
			trie.insert(nums[j])
			j++
		}
		ans[i] = trie.search(x)
	}
	return ans
}
```

```ts
class Trie {
    children: (Trie | null)[];

    constructor() {
        this.children = [null, null];
    }

    insert(x: number): void {
        let node: Trie | null = this;
        for (let i = 30; ~i; i--) {
            const v = (x >> i) & 1;
            if (node.children[v] === null) {
                node.children[v] = new Trie();
            }
            node = node.children[v] as Trie;
        }
    }

    search(x: number): number {
        let node: Trie | null = this;
        let ans = 0;
        for (let i = 30; ~i; i--) {
            const v = (x >> i) & 1;
            if (node.children[v ^ 1] !== null) {
                ans |= 1 << i;
                node = node.children[v ^ 1] as Trie;
            } else if (node.children[v] !== null) {
                node = node.children[v] as Trie;
            } else {
                return -1;
            }
        }
        return ans;
    }
}

function maximizeXor(nums: number[], queries: number[][]): number[] {
    nums.sort((a, b) => a - b);
    const n = queries.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => queries[i][1] - queries[j][1]);
    const ans: number[] = [];
    const trie = new Trie();
    let j = 0;
    for (const i of idx) {
        const x = queries[i][0];
        const m = queries[i][1];
        while (j < nums.length && nums[j] <= m) {
            trie.insert(nums[j++]);
        }
        ans[i] = trie.search(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->

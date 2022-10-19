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

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 2

    def insert(self, x):
        node = self
        for i in range(30, -1, -1):
            v = (x >> i) & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]

    def search(self, x):
        node = self
        ans = 0
        for i in range(30, -1, -1):
            v = (x >> i) & 1
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

### **Java**

```java
class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie trie = new Trie();
        Arrays.sort(nums);
        int n = queries.length;
        int[] ans = new int[n];
        int[][] qs = new int[n][3];
        for (int i = 0; i < n; ++i) {
            qs[i] = new int[] {i, queries[i][0], queries[i][1]};
        }
        Arrays.sort(qs, (a, b) -> a[2] - b[2]);
        int j = 0;
        for (var q : qs) {
            int i = q[0], x = q[1], m = q[2];
            while (j < nums.length && nums[j] <= m) {
                trie.insert(nums[j++]);
            }
            ans[i] = trie.search(x);
        }
        return ans;
    }
}

class Trie {
    Trie[] children = new Trie[2];

    public void insert(int x) {
        Trie node = this;
        for (int i = 30; i >= 0; --i) {
            int v = (x >> i) & 1;
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
            int v = (x >> i) & 1;
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
```

### **C++**

```cpp
class Trie {
public:
    Trie()
        : children(2) {}
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
        int ans = 0;
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                node = node->children[v ^ 1];
                ans |= 1 << i;
            } else if (node->children[v]) {
                node = node->children[v];
            } else {
                return -1;
            }
        }
        return ans;
    }

private:
    vector<Trie*> children;
};

class Solution {
public:
    vector<int> maximizeXor(vector<int>& nums, vector<vector<int>>& queries) {
        sort(nums.begin(), nums.end());
        int n = queries.size();
        vector<tuple<int, int, int>> qs;
        for (int i = 0; i < n; ++i) {
            qs.push_back({queries[i][1], queries[i][0], i});
        }
        sort(qs.begin(), qs.end());
        Trie* trie = new Trie();
        int j = 0;
        vector<int> ans(n);
        for (auto& [m, x, i] : qs) {
            while (j < nums.size() && nums[j] <= m) {
                trie->insert(nums[j++]);
            }
            ans[i] = trie->search(x);
        }
        return ans;
    }
};
```

### **Go**

```go
type Trie struct {
	children [2]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(x int) {
	node := this
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
	}
}

func (this *Trie) search(x int) int {
	node := this
	ans := 0
	for i := 30; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v^1] != nil {
			node = node.children[v^1]
			ans |= 1 << i
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
	type tuple struct{ i, x, m int }
	n := len(queries)
	qs := make([]tuple, n)
	for i, q := range queries {
		qs[i] = tuple{i, q[0], q[1]}
	}
	sort.Slice(qs, func(i, j int) bool { return qs[i].m < qs[j].m })
	j := 0
	ans := make([]int, n)
	trie := newTrie()
	for _, q := range qs {
		i, x, m := q.i, q.x, q.m
		for j < len(nums) && nums[j] <= m {
			trie.insert(nums[j])
			j++
		}
		ans[i] = trie.search(x)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->

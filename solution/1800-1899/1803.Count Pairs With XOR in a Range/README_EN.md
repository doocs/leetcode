# [1803. Count Pairs With XOR in a Range](https://leetcode.com/problems/count-pairs-with-xor-in-a-range)

[中文文档](/solution/1800-1899/1803.Count%20Pairs%20With%20XOR%20in%20a%20Range/README.md)

## Description

<p>Given a <strong>(0-indexed)</strong> integer array <code>nums</code> and two integers <code>low</code> and <code>high</code>, return <em>the number of <strong>nice pairs</strong></em>.</p>

<p>A <strong>nice pair</strong> is a pair <code>(i, j)</code> where <code>0 &lt;= i &lt; j &lt; nums.length</code> and <code>low &lt;= (nums[i] XOR nums[j]) &lt;= high</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,4,2,7], low = 2, high = 6

<strong>Output:</strong> 6

<strong>Explanation:</strong> All nice pairs (i, j) are as follows:

    - (0, 1): nums[0] XOR nums[1] = 5 

    - (0, 2): nums[0] XOR nums[2] = 3

    - (0, 3): nums[0] XOR nums[3] = 6

    - (1, 2): nums[1] XOR nums[2] = 6

    - (1, 3): nums[1] XOR nums[3] = 3

    - (2, 3): nums[2] XOR nums[3] = 5

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [9,8,4,2,1], low = 5, high = 14

<strong>Output:</strong> 8

<strong>Explanation:</strong> All nice pairs (i, j) are as follows:

​​​​​    - (0, 2): nums[0] XOR nums[2] = 13

&nbsp;   - (0, 3): nums[0] XOR nums[3] = 11

&nbsp;   - (0, 4): nums[0] XOR nums[4] = 8

&nbsp;   - (1, 2): nums[1] XOR nums[2] = 12

&nbsp;   - (1, 3): nums[1] XOR nums[3] = 10

&nbsp;   - (1, 4): nums[1] XOR nums[4] = 9

&nbsp;   - (2, 3): nums[2] XOR nums[3] = 6

&nbsp;   - (2, 4): nums[2] XOR nums[4] = 5</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>

    <li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>

    <li><code>1 &lt;= low &lt;= high &lt;= 2 * 10<sup>4</sup></code></li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 2
        self.cnt = 0

    def insert(self, x):
        node = self
        for i in range(15, -1, -1):
            v = x >> i & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]
            node.cnt += 1

    def search(self, x, limit):
        node = self
        ans = 0
        for i in range(15, -1, -1):
            if node is None:
                return ans
            v = x >> i & 1
            if limit >> i & 1:
                if node.children[v]:
                    ans += node.children[v].cnt
                node = node.children[v ^ 1]
            else:
                node = node.children[v]
        return ans


class Solution:
    def countPairs(self, nums: List[int], low: int, high: int) -> int:
        ans = 0
        tree = Trie()
        for x in nums:
            ans += tree.search(x, high + 1) - tree.search(x, low)
            tree.insert(x)
        return ans
```

### **Java**

```java
class Trie {
    private Trie[] children = new Trie[2];
    private int cnt;

    public void insert(int x) {
        Trie node = this;
        for (int i = 15; i >= 0; --i) {
            int v = (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
            ++node.cnt;
        }
    }

    public int search(int x, int limit) {
        Trie node = this;
        int ans = 0;
        for (int i = 15; i >= 0 && node != null; --i) {
            int v = (x >> i) & 1;
            if (((limit >> i) & 1) == 1) {
                if (node.children[v] != null) {
                    ans += node.children[v].cnt;
                }
                node = node.children[v ^ 1];
            } else {
                node = node.children[v];
            }
        }
        return ans;
    }
}

class Solution {
    public int countPairs(int[] nums, int low, int high) {
        Trie trie = new Trie();
        int ans = 0;
        for (int x : nums) {
            ans += trie.search(x, high + 1) - trie.search(x, low);
            trie.insert(x);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Trie {
public:
    Trie(): children(2), cnt(0) {}

    void insert(int x) {
        Trie* node = this;
        for (int i = 15; ~i; --i) {
            int v = x >> i & 1;
            if (!node->children[v]) {
                node->children[v] = new Trie();
            }
            node = node->children[v];
            ++node->cnt;
        }
    }

    int search(int x, int limit) {
        Trie* node = this;
        int ans = 0;
        for (int i = 15; ~i && node; --i) {
            int v = x >> i & 1;
            if (limit >> i & 1) {
                if (node->children[v]) {
                    ans += node->children[v]->cnt;
                }
                node = node->children[v ^ 1];
            } else {
                node = node->children[v];
            }
        }
        return ans;
    }

private:
    vector<Trie*> children;
    int cnt;
};

class Solution {
public:
    int countPairs(vector<int>& nums, int low, int high) {
        Trie* tree = new Trie();
        int ans = 0;
        for (int& x : nums) {
            ans += tree->search(x, high + 1) - tree->search(x, low);
            tree->insert(x);
        }
        return ans;
    }
};
```

### **Go**

```go
type Trie struct {
	children [2]*Trie
	cnt      int
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(x int) {
	node := this
	for i := 15; i >= 0; i-- {
		v := (x >> i) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
		node.cnt++
	}
}

func (this *Trie) search(x, limit int) (ans int) {
	node := this
	for i := 15; i >= 0 && node != nil; i-- {
		v := (x >> i) & 1
		if (limit >> i & 1) == 1 {
			if node.children[v] != nil {
				ans += node.children[v].cnt
			}
			node = node.children[v^1]
		} else {
			node = node.children[v]
		}
	}
	return
}

func countPairs(nums []int, low int, high int) (ans int) {
	tree := newTrie()
	for _, x := range nums {
		ans += tree.search(x, high+1) - tree.search(x, low)
		tree.insert(x)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->

---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1803.Count%20Pairs%20With%20XOR%20in%20a%20Range/README_EN.md
rating: 2479
source: Weekly Contest 233 Q4
tags:
    - Bit Manipulation
    - Trie
    - Array
---

<!-- problem:start -->

# [1803. Count Pairs With XOR in a Range](https://leetcode.com/problems/count-pairs-with-xor-in-a-range)

[中文文档](/solution/1800-1899/1803.Count%20Pairs%20With%20XOR%20in%20a%20Range/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: 0-1 Trie

For this kind of problem that counts the interval $[low, high]$, we can consider converting it into counting $[0, high]$ and $[0, low - 1]$, and then subtracting the latter from the former to get the answer.

In this problem, we can count how many pairs of numbers have an XOR value less than $high+1$, and then count how many pairs of numbers have an XOR value less than $low$. The difference between these two counts is the number of pairs whose XOR value is in the interval $[low, high]$.

Moreover, for array XOR counting problems, we can usually use a "0-1 Trie" to solve them.

The definition of the Trie node is as follows:

-   `children[0]` and `children[1]` represent the left and right child nodes of the current node, respectively;
-   `cnt` represents the number of numbers ending with the current node.

In the Trie, we also define the following two functions:

One function is $insert(x)$, which inserts the number $x$ into the Trie. This function inserts the number $x$ into the "0-1 Trie" in the order of binary bits from high to low. If the current binary bit is $0$, it is inserted into the left child node, otherwise, it is inserted into the right child node. Then the count value $cnt$ of the node is increased by $1$.

Another function is $search(x, limit)$, which searches for the count of numbers in the Trie that have an XOR value with $x$ less than $limit$. This function starts from the root node `node` of the Trie, traverses the binary bits of $x$ from high to low, and denotes the current binary bit of $x$ as $v$. If the current binary bit of $limit$ is $1$, we can directly add the count value $cnt$ of the child node that has the same binary bit $v$ as $x$ to the answer, and then move the current node to the child node that has a different binary bit $v$ from $x$, i.e., `node = node.children[v ^ 1]`. Continue to traverse the next bit. If the current binary bit of $limit$ is $0$, we can only move the current node to the child node that has the same binary bit $v$ as $x$, i.e., `node = node.children[v]`. Continue to traverse the next bit. After traversing the binary bits of $x$, return the answer.

With the above two functions, we can solve this problem.

We traverse the array `nums`. For each number $x$, we first search in the Trie for the count of numbers that have an XOR value with $x$ less than $high+1$, and then search in the Trie for the count of pairs that have an XOR value with $x$ less than $low$, and add the difference between the two counts to the answer. Then insert $x$ into the Trie. Continue to traverse the next number $x$ until the array `nums` is traversed. Finally, return the answer.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n \times \log M)$. Here, $n$ is the length of the array `nums`, and $M$ is the maximum value in the array `nums`. In this problem, we directly take $\log M = 16$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Trie {
public:
    Trie()
        : children(2)
        , cnt(0) {}

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

#### Go

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

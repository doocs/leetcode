---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0421.Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array/README.md
tags:
    - 位运算
    - 字典树
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [421. 数组中两个数的最大异或值](https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array)

[English Version](/solution/0400-0499/0421.Maximum%20XOR%20of%20Two%20Numbers%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> ，返回<em> </em><code>nums[i] XOR nums[j]</code> 的最大运算结果，其中 <code>0 ≤ i ≤ j &lt; n</code> 。</p>

<p>&nbsp;</p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,10,5,25,2,8]
<strong>输出：</strong>28
<strong>解释：</strong>最大运算结果是 5 XOR 25 = 28.</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [14,70,53,83,49,91,36,80,92,51,66,70]
<strong>输出：</strong>127
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀树

题目是求两个元素的异或最大值，可以从最高位开始考虑。

我们把数组中的每个元素 $x$ 看作一个 $32$ 位的 $01$ 串，按二进制从高位到低位的顺序，插入前缀树（最低位为叶子节点）。

搜索 $x$ 时，尽量走相反的 $01$ 字符指针的策略，因为异或运算的法则是相同得 $0$，不同得 $1$，所以我们尽可能往与 $x$ 当前位相反的字符方向走，才能得到能和 $x$ 产生最大异或值的结果。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(n \times \log M)$，其中 $n$ 是数组 $nums$ 的长度，而 $M$ 是数组中元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Trie:
    __slots__ = ("children",)

    def __init__(self):
        self.children: List[Trie | None] = [None, None]

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
            else:
                node = node.children[v]
        return ans


class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        trie = Trie()
        for x in nums:
            trie.insert(x)
        return max(trie.search(x) for x in nums)
```

#### Java

```java
class Trie {
    private Trie[] children = new Trie[2];

    public Trie() {
    }

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
            } else {
                node = node.children[v];
            }
        }
        return ans;
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int ans = 0;
        for (int x : nums) {
            trie.insert(x);
            ans = Math.max(ans, trie.search(x));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Trie {
public:
    Trie* children[2];

    Trie()
        : children{nullptr, nullptr} {}

    void insert(int x) {
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = x >> i & 1;
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
            int v = x >> i & 1;
            if (node->children[v ^ 1]) {
                ans |= 1 << i;
                node = node->children[v ^ 1];
            } else {
                node = node->children[v];
            }
        }
        return ans;
    }
};

class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        Trie* trie = new Trie();
        int ans = 0;
        for (int x : nums) {
            trie->insert(x);
            ans = max(ans, trie->search(x));
        }
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [2]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(x int) {
	node := t
	for i := 30; i >= 0; i-- {
		v := x >> i & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
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
		} else {
			node = node.children[v]
		}
	}
	return ans
}

func findMaximumXOR(nums []int) (ans int) {
	trie := newTrie()
	for _, x := range nums {
		trie.insert(x)
		ans = max(ans, trie.search(x))
	}
	return ans
}
```

#### Rust

```rust
struct Trie {
    children: [Option<Box<Trie>>; 2],
}

impl Trie {
    fn new() -> Trie {
        Trie {
            children: [None, None],
        }
    }

    fn insert(&mut self, x: i32) {
        let mut node = self;
        for i in (0..=30).rev() {
            let v = ((x >> i) & 1) as usize;
            if node.children[v].is_none() {
                node.children[v] = Some(Box::new(Trie::new()));
            }
            node = node.children[v].as_mut().unwrap();
        }
    }

    fn search(&self, x: i32) -> i32 {
        let mut node = self;
        let mut ans = 0;
        for i in (0..=30).rev() {
            let v = ((x >> i) & 1) as usize;
            if let Some(child) = &node.children[v ^ 1] {
                ans |= 1 << i;
                node = child.as_ref();
            } else {
                node = node.children[v].as_ref().unwrap();
            }
        }
        ans
    }
}

impl Solution {
    pub fn find_maximum_xor(nums: Vec<i32>) -> i32 {
        let mut trie = Trie::new();
        let mut ans = 0;
        for &x in nums.iter() {
            trie.insert(x);
            ans = ans.max(trie.search(x));
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2935.Maximum%20Strong%20Pair%20XOR%20II/README_EN.md
rating: 2348
source: Weekly Contest 371 Q4
tags:
    - Bit Manipulation
    - Trie
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [2935. Maximum Strong Pair XOR II](https://leetcode.com/problems/maximum-strong-pair-xor-ii)

[中文文档](/solution/2900-2999/2935.Maximum%20Strong%20Pair%20XOR%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. A pair of integers <code>x</code> and <code>y</code> is called a <strong>strong</strong> pair if it satisfies the condition:</p>

<ul>
	<li><code>|x - y| &lt;= min(x, y)</code></li>
</ul>

<p>You need to select two integers from <code>nums</code> such that they form a strong pair and their bitwise <code>XOR</code> is the <strong>maximum</strong> among all strong pairs in the array.</p>

<p>Return <em>the <strong>maximum</strong> </em><code>XOR</code><em> value out of all possible strong pairs in the array</em> <code>nums</code>.</p>

<p><strong>Note</strong> that you can pick the same integer twice to form a pair.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 11 strong pairs in the array <code>nums</code>: (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 3), (3, 4), (3, 5), (4, 4), (4, 5) and (5, 5).
The maximum XOR possible from these pairs is 3 XOR 4 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,100]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are 2 strong pairs in the array nums: (10, 10) and (100, 100).
The maximum XOR possible from these pairs is 10 XOR 10 = 0 since the pair (100, 100) also gives 100 XOR 100 = 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [500,520,2500,3000]
<strong>Output:</strong> 1020
<strong>Explanation:</strong> There are 6 strong pairs in the array nums: (500, 500), (500, 520), (520, 520), (2500, 2500), (2500, 3000) and (3000, 3000).
The maximum XOR possible from these pairs is 500 XOR 520 = 1020 since the only other non-zero XOR value is 2500 XOR 3000 = 636.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2<sup>20</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Trie

Observing the inequality $|x - y| \leq \min(x, y)$, which involves absolute value and minimum value, we can assume $x \leq y$, then we have $y - x \leq x$, that is, $y \leq 2x$. We can enumerate $y$ from small to large, then $x$ must satisfy the inequality $y \leq 2x$.

Therefore, we sort the array $nums$, and then enumerate $y$ from small to large. We use two pointers to maintain a window so that the elements $x$ in the window satisfy the inequality $y \leq 2x$. We can use a binary trie to maintain the elements in the window, so we can find the maximum XOR value in the window in $O(1)$ time. Each time we add $y$ to the trie, and remove the elements at the left end of the window that do not satisfy the inequality, this can ensure that the elements in the window satisfy the inequality $y \leq 2x$. Then query the maximum XOR value from the trie and update the answer.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(n \times \log M)$. Here, $n$ is the length of the array $nums$, and $M$ is the maximum value in the array $nums$. In this problem, $M = 2^{20}$.

<!-- tabs:start -->

#### Python3

```python
class Trie:
    __slots__ = ("children", "cnt")

    def __init__(self):
        self.children: List[Trie | None] = [None, None]
        self.cnt = 0

    def insert(self, x: int):
        node = self
        for i in range(20, -1, -1):
            v = x >> i & 1
            if node.children[v] is None:
                node.children[v] = Trie()
            node = node.children[v]
            node.cnt += 1

    def search(self, x: int) -> int:
        node = self
        ans = 0
        for i in range(20, -1, -1):
            v = x >> i & 1
            if node.children[v ^ 1] and node.children[v ^ 1].cnt:
                ans |= 1 << i
                node = node.children[v ^ 1]
            else:
                node = node.children[v]
        return ans

    def remove(self, x: int):
        node = self
        for i in range(20, -1, -1):
            v = x >> i & 1
            node = node.children[v]
            node.cnt -= 1


class Solution:
    def maximumStrongPairXor(self, nums: List[int]) -> int:
        nums.sort()
        tree = Trie()
        ans = i = 0
        for y in nums:
            tree.insert(y)
            while y > nums[i] * 2:
                tree.remove(nums[i])
                i += 1
            ans = max(ans, tree.search(y))
        return ans
```

#### Java

```java
class Trie {
    private Trie[] children = new Trie[2];
    private int cnt = 0;

    public Trie() {
    }

    public void insert(int x) {
        Trie node = this;
        for (int i = 20; i >= 0; --i) {
            int v = x >> i & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
            ++node.cnt;
        }
    }

    public int search(int x) {
        Trie node = this;
        int ans = 0;
        for (int i = 20; i >= 0; --i) {
            int v = x >> i & 1;
            if (node.children[v ^ 1] != null && node.children[v ^ 1].cnt > 0) {
                ans |= 1 << i;
                node = node.children[v ^ 1];
            } else {
                node = node.children[v];
            }
        }
        return ans;
    }

    public void remove(int x) {
        Trie node = this;
        for (int i = 20; i >= 0; --i) {
            int v = x >> i & 1;
            node = node.children[v];
            --node.cnt;
        }
    }
}

class Solution {
    public int maximumStrongPairXor(int[] nums) {
        Arrays.sort(nums);
        Trie tree = new Trie();
        int ans = 0, i = 0;
        for (int y : nums) {
            tree.insert(y);
            while (y > nums[i] * 2) {
                tree.remove(nums[i++]);
            }
            ans = Math.max(ans, tree.search(y));
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
    int cnt;

    Trie()
        : cnt(0) {
        children[0] = nullptr;
        children[1] = nullptr;
    }

    void insert(int x) {
        Trie* node = this;
        for (int i = 20; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v] == nullptr) {
                node->children[v] = new Trie();
            }
            node = node->children[v];
            ++node->cnt;
        }
    }

    int search(int x) {
        Trie* node = this;
        int ans = 0;
        for (int i = 20; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1] != nullptr && node->children[v ^ 1]->cnt > 0) {
                ans |= 1 << i;
                node = node->children[v ^ 1];
            } else {
                node = node->children[v];
            }
        }
        return ans;
    }

    void remove(int x) {
        Trie* node = this;
        for (int i = 20; ~i; --i) {
            int v = (x >> i) & 1;
            node = node->children[v];
            --node->cnt;
        }
    }
};

class Solution {
public:
    int maximumStrongPairXor(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        Trie* tree = new Trie();
        int ans = 0, i = 0;
        for (int y : nums) {
            tree->insert(y);
            while (y > nums[i] * 2) {
                tree->remove(nums[i++]);
            }
            ans = max(ans, tree->search(y));
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

func (t *Trie) insert(x int) {
	node := t
	for i := 20; i >= 0; i-- {
		v := (x >> uint(i)) & 1
		if node.children[v] == nil {
			node.children[v] = newTrie()
		}
		node = node.children[v]
		node.cnt++
	}
}

func (t *Trie) search(x int) int {
	node := t
	ans := 0
	for i := 20; i >= 0; i-- {
		v := (x >> uint(i)) & 1
		if node.children[v^1] != nil && node.children[v^1].cnt > 0 {
			ans |= 1 << uint(i)
			node = node.children[v^1]
		} else {
			node = node.children[v]
		}
	}
	return ans
}

func (t *Trie) remove(x int) {
	node := t
	for i := 20; i >= 0; i-- {
		v := (x >> uint(i)) & 1
		node = node.children[v]
		node.cnt--
	}
}

func maximumStrongPairXor(nums []int) (ans int) {
	sort.Ints(nums)
	tree := newTrie()
	i := 0
	for _, y := range nums {
		tree.insert(y)
		for ; y > nums[i]*2; i++ {
			tree.remove(nums[i])
		}
		ans = max(ans, tree.search(y))
	}
	return ans
}
```

#### TypeScript

```ts
class Trie {
    children: (Trie | null)[];
    cnt: number;

    constructor() {
        this.children = [null, null];
        this.cnt = 0;
    }

    insert(x: number): void {
        let node: Trie | null = this;
        for (let i = 20; i >= 0; i--) {
            const v = (x >> i) & 1;
            if (node.children[v] === null) {
                node.children[v] = new Trie();
            }
            node = node.children[v] as Trie;
            node.cnt++;
        }
    }

    search(x: number): number {
        let node: Trie | null = this;
        let ans = 0;
        for (let i = 20; i >= 0; i--) {
            const v = (x >> i) & 1;
            if (node.children[v ^ 1] !== null && (node.children[v ^ 1] as Trie).cnt > 0) {
                ans |= 1 << i;
                node = node.children[v ^ 1] as Trie;
            } else {
                node = node.children[v] as Trie;
            }
        }
        return ans;
    }

    remove(x: number): void {
        let node: Trie | null = this;
        for (let i = 20; i >= 0; i--) {
            const v = (x >> i) & 1;
            node = node.children[v] as Trie;
            node.cnt--;
        }
    }
}

function maximumStrongPairXor(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const tree = new Trie();
    let ans = 0;
    let i = 0;

    for (const y of nums) {
        tree.insert(y);

        while (y > nums[i] * 2) {
            tree.remove(nums[i++]);
        }

        ans = Math.max(ans, tree.search(y));
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

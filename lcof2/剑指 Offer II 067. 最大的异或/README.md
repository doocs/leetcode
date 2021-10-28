# [剑指 Offer II 067. 最大的异或](https://leetcode-cn.com/problems/ms70jA)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code> ，返回<em> </em><code>nums[i] XOR nums[j]</code> 的最大运算结果，其中 <code>0 &le; i &le; j &lt; n</code> 。</p>

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
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4]
<strong>输出：</strong>6
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>nums = [8,10,2]
<strong>输出：</strong>10
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>nums = [14,70,53,83,49,91,36,80,92,51,66,70]
<strong>输出：</strong>127
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>
</div>
</div>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以在 <code>O(n)</code> 的时间解决这个问题吗？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 421&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/">https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/</a></p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表或前缀树实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        max = 0
        mask = 0
        for i in range(30, -1, -1):
            current = 1 << i
            # 期望的二进制前缀
            mask = mask ^ current
            # 在当前前缀下, 数组内的前缀位数所有情况集合
            s = set()
            for num in nums:
                s.add(num & mask)
            # 期望最终异或值的从右数第i位为1, 再根据异或运算的特性推算假设是否成立
            flag = max | current
            for prefix in s:
                if prefix ^ flag in s:
                    max = flag
                    break
        return max
```

```python
class Trie:
    def __init__(self):
        self.left = None
        self.right = None

class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        self.root = Trie()
        self.highest = 30

        def add(num):
            node = self.root
            for i in range(self.highest, -1, -1):
                bit = (num >> i) & 1
                if bit == 0:
                    if node.left is None:
                        node.left = Trie()
                    node = node.left
                else:
                    if node.right is None:
                        node.right = Trie()
                    node = node.right

        def cal(num):
            node = self.root
            res = 0
            for i in range(self.highest, -1, -1):
                bit = (num >> i) & 1
                if bit == 0:
                    if node.right:
                        res = res * 2 + 1
                        node = node.right
                    else:
                        res = res * 2
                        node = node.left
                else:
                    if node.left:
                        res = res * 2 + 1
                        node = node.left
                    else:
                        res = res * 2
                        node = node.right
            return res

        res = 0
        for i in range(1, len(nums)):
            add(nums[i - 1])
            res = max(res, cal(nums[i]))
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int findMaximumXOR(int[] numbers) {
        int max = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            int current = 1 << i;
            // 期望的二进制前缀
            mask = mask ^ current;
            // 在当前前缀下, 数组内的前缀位数所有情况集合
            Set<Integer> set = new HashSet<>();
            for (int j = 0, k = numbers.length; j < k; j++) {
                set.add(mask & numbers[j]);
            }
            // 期望最终异或值的从右数第i位为1, 再根据异或运算的特性推算假设是否成立
            int flag = max | current;
            for (Integer prefix : set) {
                if (set.contains(prefix ^ flag)) {
                    max = flag;
                    break;
                }
            }
        }
        return max;
    }
}
```

前缀树。

```java
class Solution {
    private static final int HIGHEST = 30;
    private Trie root;

    public int findMaximumXOR(int[] nums) {
        int res = 0;
        root = new Trie();
        for (int i = 1; i < nums.length; ++i) {
            add(nums[i - 1]);
            res = Math.max(res, cal(nums[i]));
        }
        return res;
    }

    private int cal(int num) {
        Trie node = root;
        int res = 0;
        for (int i = HIGHEST; i >= 0; --i) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (node.right != null) {
                    res = res * 2 + 1;
                    node = node.right;
                } else {
                    res = res * 2;
                    node = node.left;
                }
            } else {
                if (node.left != null) {
                    res = res * 2 + 1;
                    node = node.left;
                } else {
                    res = res * 2;
                    node = node.right;
                }
            }
        }
        return res;
    }

    private void add(int num) {
        Trie node = root;
        for (int i = HIGHEST; i >= 0; --i) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (node.left == null) {
                    node.left = new Trie();
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new Trie();
                }
                node = node.right;
            }
        }
    }
}

class Trie {
    public Trie left;
    public Trie right;
}
```

### **C++**

```cpp
class Trie {
public:
    Trie* left;
    Trie* right;
};

class Solution {
public:
    int highest = 30;
    Trie* root;

    int findMaximumXOR(vector<int>& nums) {
        root = new Trie();
        int res = 0;
        for (int i = 1; i < nums.size(); ++i)
        {
            add(nums[i - 1]);
            res = max(res, cal(nums[i]));
        }
        return res;
    }

    int cal(int num) {
        Trie* node = root;
        int res = 0;
        for (int i = highest; i >= 0; --i)
        {
            int bit = (num >> i) & 1;
            if (bit == 0)
            {
                if (node->right)
                {
                    res = res * 2 + 1;
                    node = node->right;
                }
                else
                {
                    res = res * 2;
                    node = node->left;
                }
            }
            else
            {
                if (node->left)
                {
                    res = res * 2 + 1;
                    node = node->left;
                }
                else
                {
                    res = res * 2;
                    node = node->right;
                }
            }
        }
        return res;
    }

    void add(int num) {
        Trie* node = root;
        for (int i = highest; i >= 0; --i)
        {
            int bit = (num >> i) & 1;
            if (bit == 0)
            {
                if (!node->left) node->left = new Trie();
                node = node->left;
            }
            else
            {
                if (!node->right) node->right = new Trie();
                node = node->right;
            }
        }
    }
};
```

### **Go**

```go
const highest = 30

type trie struct {
	left, right *trie
}

func (root *trie) add(num int) {
	node := root
	for i := highest; i >= 0; i-- {
		bit := (num >> i) & 1
		if bit == 0 {
			if node.left == nil {
				node.left = &trie{}
			}
			node = node.left
		} else {
			if node.right == nil {
				node.right = &trie{}
			}
			node = node.right
		}
	}
}

func (root *trie) cal(num int) int {
	node := root
	res := 0
	for i := highest; i >= 0; i-- {
		bit := (num >> i) & 1
		if bit == 0 {
			if node.right != nil {
				res = res*2 + 1
				node = node.right
			} else {
				res = res * 2
				node = node.left
			}
		} else {
			if node.left != nil {
				res = res*2 + 1
				node = node.left
			} else {
				res = res * 2
				node = node.right
			}
		}
	}
	return res
}

func findMaximumXOR(nums []int) int {
	root := &trie{}
	res := 0
	for i := 1; i < len(nums); i++ {
		root.add(nums[i-1])
		res = max(res, root.cal(nums[i]))
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->

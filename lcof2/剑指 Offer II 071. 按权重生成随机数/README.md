# [剑指 Offer II 071. 按权重生成随机数](https://leetcode.cn/problems/cuyjEf)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数数组&nbsp;<code>w</code> ，其中&nbsp;<code>w[i]</code>&nbsp;代表下标 <code>i</code>&nbsp;的权重（下标从 <code>0</code> 开始），请写一个函数&nbsp;<code>pickIndex</code>&nbsp;，它可以随机地获取下标 <code>i</code>，选取下标 <code>i</code>&nbsp;的概率与&nbsp;<code>w[i]</code>&nbsp;成正比。</p>

<ol>
</ol>

<p>例如，对于 <code>w = [1, 3]</code>，挑选下标 <code>0</code> 的概率为 <code>1 / (1 + 3)&nbsp;= 0.25</code> （即，25%），而选取下标 <code>1</code> 的概率为 <code>3 / (1 + 3)&nbsp;= 0.75</code>（即，75%）。</p>

<p>也就是说，选取下标 <code>i</code> 的概率为 <code>w[i] / sum(w)</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
inputs = [&quot;Solution&quot;,&quot;pickIndex&quot;]
inputs = [[[1]],[]]
<strong>输出：</strong>
[null,0]
<strong>解释：</strong>
Solution solution = new Solution([1]);
solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
inputs = [&quot;Solution&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;,&quot;pickIndex&quot;]
inputs = [[[1,3]],[],[],[],[],[]]
<strong>输出：</strong>
[null,1,1,1,1,0]
<strong>解释：</strong>
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
诸若此类。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= w.length &lt;= 10000</code></li>
	<li><code>1 &lt;= w[i] &lt;= 10^5</code></li>
	<li><code>pickIndex</code>&nbsp;将被调用不超过&nbsp;<code>10000</code>&nbsp;次</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 528&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/random-pick-with-weight/">https://leetcode.cn/problems/random-pick-with-weight/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀和 + 二分查找”。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def __init__(self, w: List[int]):
        n = len(w)
        self.presum = [0] * (n + 1)
        for i in range(n):
            self.presum[i + 1] = self.presum[i] + w[i]

    def pickIndex(self) -> int:
        n = len(self.presum)
        x = random.randint(1, self.presum[-1])
        left, right = 0, n - 2
        while left < right:
            mid = (left + right) >> 1
            if self.presum[mid + 1] >= x:
                right = mid
            else:
                left = mid + 1
        return left


# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] presum;

    public Solution(int[] w) {
        int n = w.length;
        presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + w[i];
        }
    }

    public int pickIndex() {
        int n = presum.length;
        int x = (int) (Math.random() * presum[n - 1]) + 1;
        int left = 0, right = n - 2;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (presum[mid + 1] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
```

### **C++**

```cpp
class Solution {
public:
    vector<int> presum;

    Solution(vector<int>& w) {
        int n = w.size();
        presum.resize(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + w[i];
    }

    int pickIndex() {
        int n = presum.size();
        int x = rand() % presum[n - 1] + 1;
        int left = 0, right = n - 2;
        while (left < right) {
            int mid = left + right >> 1;
            if (presum[mid + 1] >= x)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(w);
 * int param_1 = obj->pickIndex();
 */
```

### **Go**

```go
type Solution struct {
	presum []int
}

func Constructor(w []int) Solution {
	n := len(w)
	pre := make([]int, n+1)
	for i := 0; i < n; i++ {
		pre[i+1] = pre[i] + w[i]
	}
	return Solution{pre}
}

func (this *Solution) PickIndex() int {
	n := len(this.presum)
	x := rand.Intn(this.presum[n-1]) + 1
	left, right := 0, n-2
	for left < right {
		mid := (left + right) >> 1
		if this.presum[mid+1] >= x {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

/**
 * Your Solution object will be instantiated and called as such:
 * obj := Constructor(w);
 * param_1 := obj.PickIndex();
 */
```

### **...**

```

```

<!-- tabs:end -->

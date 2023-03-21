# [1104. 二叉树寻路](https://leetcode.cn/problems/path-in-zigzag-labelled-binary-tree)

[English Version](/solution/1100-1199/1104.Path%20In%20Zigzag%20Labelled%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 <strong>逐行</strong> 依次按&nbsp;&ldquo;之&rdquo; 字形进行标记。</p>

<p>如下图所示，在奇数行（即，第一行、第三行、第五行&hellip;&hellip;）中，按从左到右的顺序进行标记；</p>

<p>而偶数行（即，第二行、第四行、第六行&hellip;&hellip;）中，按从右到左的顺序进行标记。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1104.Path%20In%20Zigzag%20Labelled%20Binary%20Tree/images/tree.png" style="height: 138px; width: 300px;"></p>

<p>给你树上某一个节点的标号 <code>label</code>，请你返回从根节点到该标号为 <code>label</code> 节点的路径，该路径是由途经的节点标号所组成的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>label = 14
<strong>输出：</strong>[1,3,4,14]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>label = 26
<strong>输出：</strong>[1,2,6,10,26]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= label &lt;= 10^6</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

对于一棵完全二叉树，第 $i$ 行的节点个数为 $2^{i-1}$，第 $i$ 行的节点编号范围为 $[2^{i-1}, 2^i - 1]$。而题目中对于奇数行，按从左到右的顺序进行标记，对于偶数行，按从右到左的顺序进行标记。所以对于第 $i$ 行的节点 $label$，它的互补节点编号为 $2^{i-1} + 2^i - 1 - label$。所以节点 $label$ 的实际父节点编号为 $(2^{i-1} + 2^i - 1 - label) / 2$。我们可以通过不断地求互补节点编号和父节点编号，直到到达根节点，即可得到从根节点到节点 $label$ 的路径。

最后，我们需要将路径反转，因为题目要求路径是从根节点到节点 $label$ 的路径。

时间复杂度 $O(\log n)$，其中 $n$ 为节点 $label$ 的编号。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def pathInZigZagTree(self, label: int) -> List[int]:
        x = i = 1
        while (x << 1) <= label:
            x <<= 1
            i += 1
        ans = [0] * i
        while i:
            ans[i - 1] = label
            label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1
            i -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int x = 1, i = 1;
        while ((x << 1) <= label) {
            x <<= 1;
            ++i;
        }
        List<Integer> ans = new ArrayList<>();
        for (; i > 0; --i) {
            ans.add(label);
            label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1;
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> pathInZigZagTree(int label) {
        int x = 1, i = 1;
        while ((x << 1) <= label) {
            x <<= 1;
            ++i;
        }
        vector<int> ans;
        for (; i > 0; --i) {
            ans.push_back(label);
            label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func pathInZigZagTree(label int) (ans []int) {
	x, i := 1, 1
	for x<<1 <= label {
		x <<= 1
		i++
	}
	for ; i > 0; i-- {
		ans = append(ans, label)
		label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->

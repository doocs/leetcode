# [1104. Path In Zigzag Labelled Binary Tree](https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree)

[中文文档](/solution/1100-1199/1104.Path%20In%20Zigzag%20Labelled%20Binary%20Tree/README.md)

## Description

<p>In an infinite binary tree where every node has two children, the nodes are labelled in row order.</p>

<p>In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1104.Path%20In%20Zigzag%20Labelled%20Binary%20Tree/images/tree.png" style="width: 300px; height: 138px;" /></p>

<p>Given the <code>label</code> of a node in this tree, return the labels in the path from the root of the tree to the&nbsp;node with that <code>label</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> label = 14
<strong>Output:</strong> [1,3,4,14]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> label = 26
<strong>Output:</strong> [1,2,6,10,26]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= label &lt;= 10^6</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

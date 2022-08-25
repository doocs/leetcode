# [1441. 用栈操作构建数组](https://leetcode.cn/problems/build-an-array-with-stack-operations)

[English Version](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>target</code> 和一个整数 <code>n</code>。每次迭代，需要从&nbsp; <code>list = { 1 , 2 , 3 ..., n }</code> 中依次读取一个数字。</p>

<p>请使用下述操作来构建目标数组 <code>target</code> ：</p>

<ul>
	<li><code>"Push"</code>：从 <code>list</code> 中读取一个新元素， 并将其推入数组中。</li>
	<li><code>"Pop"</code>：删除数组中的最后一个元素。</li>
	<li>如果目标数组构建完成，就停止读取更多元素。</li>
</ul>

<p>题目数据保证目标数组严格递增，并且只包含 <code>1</code> 到 <code>n</code> 之间的数字。</p>

<p>请返回构建目标数组所用的操作序列。如果存在多个可行方案，返回任一即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = [1,3], n = 3
<strong>输出：</strong>["Push","Push","Pop","Push"]
<strong>解释： 
</strong>读取 1 并自动推入数组 -&gt; [1]
读取 2 并自动推入数组，然后删除它 -&gt; [1]
读取 3 并自动推入数组 -&gt; [1,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2,3], n = 3
<strong>输出：</strong>["Push","Push","Push"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2], n = 4
<strong>输出：</strong>["Push","Push"]
<strong>解释：</strong>只需要读取前 2 个数字就可以停止。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= target[i] &lt;= n</code></li>
	<li><code>target</code> 严格递增</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        cur, ans = 1, []
        for t in target:
            for i in range(cur, n + 1):
                ans.append('Push')
                if t == i:
                    cur = i + 1
                    break
                ans.append('Pop')
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int cur = 1;
        for (int t : target) {
            for (int i = cur; i <= n; ++i) {
                ans.add("Push");
                if (t == i) {
                    cur = i + 1;
                    break;
                }
                ans.add("Pop");
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        vector<string> ans;
        int cur = 1;
        for (int t : target) {
            for (int i = cur; i <= n; ++i) {
                ans.push_back("Push");
                if (t == i) {
                    cur = i + 1;
                    break;
                }
                ans.push_back("Pop");
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func buildArray(target []int, n int) []string {
	var ans []string
	cur := 1
	for _, t := range target {
		for i := cur; i <= n; i++ {
			ans = append(ans, "Push")
			if t == i {
				cur = i + 1
				break
			}
			ans = append(ans, "Pop")
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->

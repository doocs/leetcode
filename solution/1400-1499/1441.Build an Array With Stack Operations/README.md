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

**方法一：模拟**

我们定义 $cur$ 表示当前已经从 `list` 中读取到的数字，初始时 $cur = 0$，用一个数组 $ans$ 存储答案。

遍历数组 `target`，对于每个数字 $v$，如果当前要从 `list` 读取的数字小于 $v$，那么我们应该执行 `Push` 和 `Pop` 操作，直到读取的数字等于 $v$，然后执行 `Push` 操作，这样就可以得到数字 $v$。

遍历结束后，也就构建出了数组 `target`，返回 `ans` 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组 `target` 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        cur, ans = 0, []
        for v in target:
            cur += 1
            while cur < v:
                ans.extend(['Push', 'Pop'])
                cur += 1
            ans.append('Push')
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        int cur = 0;
        List<String> ans = new ArrayList<>();
        for (int v : target) {
            while (++cur < v) {
                ans.add("Push");
                ans.add("Pop");
            }
            ans.add("Push");
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
        int cur = 0;
        vector<string> ans;
        for (int& v : target) {
            while (++cur < v) {
                ans.emplace_back("Push");
                ans.emplace_back("Pop");
            }
            ans.emplace_back("Push");
        }
        return ans;
    }
};
```

### **Go**

```go
func buildArray(target []int, n int) []string {
	cur := 0
	ans := []string{}
	for _, v := range target {
		for cur = cur + 1; cur < v; cur++ {
			ans = append(ans, "Push", "Pop")
		}
		ans = append(ans, "Push")
	}
	return ans
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char **buildArray(int *target, int targetSize, int n, int *returnSize) {
    char **res = (char **) malloc(sizeof(char *) * n * 2);
    int cur = 1;
    int i = 0;
    for (int j = 0; j < targetSize; j++) {
        while (++cur < target[j]) {
            res[i] = (char *) malloc(sizeof(char) * 8);
            strcpy(res[i++], "Push");
            res[i] = (char *) malloc(sizeof(char) * 8);
            strcpy(res[i++], "Pop");
        }
        res[i] = (char *) malloc(sizeof(char) * 8);
        strcpy(res[i++], "Push");
    }
    *returnSize = i;
    return res;
}
```

### **TypeScript**

```ts
function buildArray(target: number[], n: number): string[] {
    const res = [];
    let cur = 0;
    for (const num of target) {
        while (++cur < num) {
            res.push('Push', 'Pop');
        }
        res.push('Push');
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn build_array(target: Vec<i32>, n: i32) -> Vec<String> {
        let mut res = Vec::new();
        let mut cur = 1;
        for &num in target.iter() {
            while cur < num {
                res.push("Push");
                res.push("Pop");
                cur += 1;
            }
            res.push("Push");
            cur += 1;
        }
        res.into_iter().map(String::from).collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->

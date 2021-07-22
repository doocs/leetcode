# [997. 找到小镇的法官](https://leetcode-cn.com/problems/find-the-town-judge)

[English Version](/solution/0900-0999/0997.Find%20the%20Town%20Judge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个小镇里，按从 <code>1</code> 到 <code>N</code> 标记了&nbsp;<code>N</code> 个人。传言称，这些人中有一个是小镇上的秘密法官。</p>

<p>如果小镇的法官真的存在，那么：</p>

<ol>
	<li>小镇的法官不相信任何人。</li>
	<li>每个人（除了小镇法官外）都信任小镇的法官。</li>
	<li>只有一个人同时满足属性 1 和属性 2 。</li>
</ol>

<p>给定数组&nbsp;<code>trust</code>，该数组由信任对 <code>trust[i] = [a, b]</code>&nbsp;组成，表示标记为 <code>a</code> 的人信任标记为 <code>b</code> 的人。</p>

<p>如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>N = 2, trust = [[1,2]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>N = 3, trust = [[1,3],[2,3]]
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>N = 3, trust = [[1,3],[2,3],[3,1]]
<strong>输出：</strong>-1
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>N = 3, trust = [[1,2],[2,3]]
<strong>输出：</strong>-1
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
<strong>输出：</strong>3</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 1000</code></li>
	<li><code>trust.length &lt;= 10000</code></li>
	<li><code>trust[i]</code>&nbsp;是完全不同的</li>
	<li><code>trust[i][0] != trust[i][1]</code></li>
	<li><code>1 &lt;= trust[i][0], trust[i][1] &lt;= N</code></li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        if n == 1 and len(trust) == 0:
            return 1
        dic = {}
        values = set()
        for i in trust:
            values.add(i[0])
            if i[1] in dic:
                dic[i[1]].append(i[0])
            else:
                dic[i[1]] = [i[0]]

        for key, value in dic.items():
            if len(dic[key]) == n-1 and key not in values:
                return k
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function findJudge(n: number, trust: number[][]): number {
    let candidates = new Array(n).fill(0);
    for (let [a, b] of trust) {
        candidates[a - 1] = -1;
        if (candidates[b - 1] >= 0) {
            candidates[b - 1]++;
        }
    }
    
    for (let i = 0; i < n; i++) {
        if (candidates[i] == n - 1) {
            return i + 1;
        }
    }
    return -1;
};
```

### **...**

```

```

<!-- tabs:end -->

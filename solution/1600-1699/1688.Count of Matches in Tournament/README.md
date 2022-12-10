# [1688. 比赛中的配对次数](https://leetcode.cn/problems/count-of-matches-in-tournament)

[English Version](/solution/1600-1699/1688.Count%20of%20Matches%20in%20Tournament/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，表示比赛中的队伍数。比赛遵循一种独特的赛制：</p>

<ul>
	<li>如果当前队伍数是 <strong>偶数</strong> ，那么每支队伍都会与另一支队伍配对。总共进行 <code>n / 2</code> 场比赛，且产生 <code>n / 2</code> 支队伍进入下一轮。</li>
	<li>如果当前队伍数为 <strong>奇数</strong> ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 <code>(n - 1) / 2</code> 场比赛，且产生 <code>(n - 1) / 2 + 1</code> 支队伍进入下一轮。</li>
</ul>

<p>返回在比赛中进行的配对次数，直到决出获胜队伍为止。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 7
<strong>输出：</strong>6
<strong>解释：</strong>比赛详情：
- 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
- 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
- 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
总配对次数 = 3 + 2 + 1 = 6
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 14
<strong>输出：</strong>13
<strong>解释：</strong>比赛详情：
- 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
- 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。 
- 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
- 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
总配对次数 = 7 + 3 + 2 + 1 = 13
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

根据题目描述我们知道，一共有 $n$ 支队伍，每一次的配对，都会淘汰一支队伍，所以配对次数就是淘汰的队伍数，即 $n - 1$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfMatches(self, n: int) -> int:
        return n - 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfMatches(int n) {
        return n - 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfMatches(int n) {
        return n - 1;
    }
};
```

### **Go**

```go
func numberOfMatches(n int) int {
	return n - 1
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var numberOfMatches = function (n) {
    return n - 1;
};
```

### **TypeScript**

```ts
function numberOfMatches(n: number): number {
    return n - 1;
}
```

### **...**

```

```

<!-- tabs:end -->

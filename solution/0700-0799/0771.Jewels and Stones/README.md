# [771. 宝石与石头](https://leetcode.cn/problems/jewels-and-stones)

[English Version](/solution/0700-0799/0771.Jewels%20and%20Stones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>&nbsp;给你一个字符串 <code>jewels</code>&nbsp;代表石头中宝石的类型，另有一个字符串 <code>stones</code> 代表你拥有的石头。&nbsp;<code>stones</code>&nbsp;中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。</p>

<p>字母区分大小写，因此 <code>"a"</code> 和 <code>"A"</code> 是不同类型的石头。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>jewels = "aA", stones = "aAAbbbb"
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>jewels = "z", stones = "ZZ"
<strong>输出：</strong>0<strong>
</strong></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;jewels.length, stones.length &lt;= 50</code></li>
	<li><code>jewels</code> 和 <code>stones</code> 仅由英文字母组成</li>
	<li><code>jewels</code> 中的所有字符都是 <strong>唯一的</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表或数组**

我们可以先用一个哈希表或数组 $s$ 记录所有宝石的类型。然后遍历所有石头，如果当前石头是宝石，就将答案加一。

时间复杂度 $O(m+n)$。其中 $m$ 和 $n$ 分别是字符串 $jewels$ 和 $stones$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        s = set(jewels)
        return sum(c in s for c in stones)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int[] s = new int[128];
        for (char c : jewels.toCharArray()) {
            s[c] = 1;
        }
        int ans = 0;
        for (char c : stones.toCharArray()) {
            ans += s[c];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numJewelsInStones(string jewels, string stones) {
        int s[128] = {0};
        for (char c : jewels) s[c] = 1;
        int ans = 0;
        for (char c : stones) ans += s[c];
        return ans;
    }
};
```

### **Go**

```go
func numJewelsInStones(jewels string, stones string) (ans int) {
	s := make([]int, 128)
	for _, c := range jewels {
		s[c] = 1
	}
	for _, c := range stones {
		ans += s[c]
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {string} jewels
 * @param {string} stones
 * @return {number}
 */
var numJewelsInStones = function (jewels, stones) {
    const s = new Set(jewels.split(''));
    return stones.split('').reduce((prev, val) => prev + s.has(val), 0);
};
```

### **TypeScript**

```ts
function numJewelsInStones(jewels: string, stones: string): number {
    const set = new Set([...jewels]);
    let ans = 0;
    for (const c of stones) {
        set.has(c) && ans++;
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn num_jewels_in_stones(jewels: String, stones: String) -> i32 {
        let mut set = jewels.as_bytes().iter().collect::<HashSet<&u8>>();
        let mut ans = 0;
        for c in stones.as_bytes() {
            if set.contains(c) {
                ans += 1;
            }
        }
        ans
    }
}
```

### **C**

```c
int numJewelsInStones(char *jewels, char *stones) {
    int set[128] = {0};
    for (int i = 0; jewels[i]; i++) {
        set[jewels[i]] = 1;
    }
    int ans = 0;
    for (int i = 0; stones[i]; i++) {
        set[stones[i]] && ans++;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
